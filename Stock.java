/**
 * This class represents a Stock object. A Stick object holds the stock symbol, the 
 * company name, the lowest and highest sell prices, and the volume for the "day." 
 * It also has the priority queue for sell orders and another priority queue for buy 
 * orders for that stock. This class also includes an algorithm for executing orders.
 *  
 * CSC630 Period 8 Ms. Litvin
 * Date: March 4, 2021
 * Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
 * 
 * Citation: Decimal Format information found at 
 * https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
 * 
 * Citation: Spoke with Yury Shmuylovich on 03/03/21 about what "market" means. 
*/
import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock
{
  //Format prices
  public static DecimalFormat money = new DecimalFormat("0.00");

  private String stockSymbol, companyName;
  private double loPrice, hiPrice, latestSalePrice, askMarketPrice, bidMarketPrice;
  private int volume;
  private PriorityQueue<TradeOrder> buyOrders, sellOrders;

  /**
   * Initializes a stock and its components. 
   */
  public Stock(String symbol, String name, double price)
  {
    stockSymbol = symbol;
    companyName = name;
    
    // because there are no sales yet for this stock, the following 4 variables are 
    // initialized to the given price and the volume is initialized to 0
    loPrice = price;
    hiPrice = price;
    latestSalePrice = price;
    askMarketPrice = price;
    bidMarketPrice = price;
    volume = 0;
    
    // initialize the buying queue in ascending order
    buyOrders = new PriorityQueue<TradeOrder>(new PriceComparator()); 
    // initialize the selling queue in descending order
    sellOrders = new PriorityQueue<TradeOrder>(new PriceComparator(false)); 
  }

  /**
   * Returns a formatted stock quote with information on the following: company name, 
   * stock symbol, price of last sale for this stock, price of most expensive sale 
   * for this stock, price of least expensive sale for this stock, how many shares of 
   * the stock were sold, the market price in terms of asking and bidding. 
   */
  public String getQuote()
  {
    String quote = companyName + " (" + stockSymbol + ")\nPrice: " +
        money.format(latestSalePrice) + " hi: " + money.format(hiPrice) + 
        " lo: " + money.format(loPrice) + " vol: " + volume + "\n";

    // Market for selling 
    String ask = "Ask: none";
    if (!sellOrders.isEmpty())
    { 
      if(sellOrders.peek().isMarket())
        ask = "Ask: market size: " + sellOrders.peek().getNumShares();
      else
      {
        ask = "Ask: " + money.format(askMarketPrice) + " size: "
            + sellOrders.peek().getNumShares();
      }
    }
    
    //Market for bidding 
    String bid = "Bid: none";
    if (!buyOrders.isEmpty())
    {
      if(buyOrders.peek().isMarket())
        bid = "Bid: market size: " + buyOrders.peek().getNumShares();
      else
      {
        bid = "Bid: " + money.format(bidMarketPrice) + " size: "
          + buyOrders.peek().getNumShares();
      }
    }

    return quote + ask + " " + bid;
  }

  /**
   * Adds the given order to the proper queue (depending on if it is a buy or a sell),
   * sends a formatted message to trader that placed the order, and executes the orders. 
   */
  public void placeOrder(TradeOrder order)
  {
    String str = "New order: ";

    if (order.isBuy())
    {
      buyOrders.add(order);
      str += "Buy ";
      bidMarketPrice = buyOrders.peek().getPrice();
    }
    else
    {
      sellOrders.add(order);
      str += "Sell ";
      askMarketPrice = sellOrders.peek().getPrice();
    }

    str += order.getSymbol() + " (" + this.companyName + ")" + "\n"
        + order.getNumShares() + " shares at ";

    if (!order.isMarket())
      str += money.format(order.getPrice());
    else
      str += "market";

    order.getTrader().receiveMessages(str);
    executeOrders();
  }

  /**
   * Executes all possible orders based on the priority of the buy and sell queues. 
   * Updates orders if only partially executed and updates the loPrice, hiPrice, and volume
   * fields as appropriate for the sales of a stock. Sends a message to the traders involved
   * in the exchange detailing the transaction. 
   */
  public void executeOrders()
  {
    // Base case for recursive method, if there are no sell or buy offers nothing can be exchanged
    if (buyOrders.isEmpty() || sellOrders.isEmpty())
      return;

    TradeOrder buy = buyOrders.peek();
    TradeOrder sell = sellOrders.peek();
    Trader buyer = buy.getTrader();
    Trader seller = sell.getTrader();

    // Calculate the price
    if(buy.isMarket() && sell.isMarket())
    {
      askMarketPrice = latestSalePrice;
      bidMarketPrice = latestSalePrice;
    }
    else if (buy.isMarket())
      latestSalePrice = askMarketPrice;
    else if (sell.isMarket())
      latestSalePrice = bidMarketPrice;
    else
    {
      if(buy.getPrice() >= sell.getPrice())
        latestSalePrice = sell.getPrice();
      else // sell price > buy price, does nothing
        return;
    }
    
    // Calculate the number of shares being exchanged
    int sharesExchanged;

    if (sell.getNumShares() >= buy.getNumShares())
      sharesExchanged = buy.getNumShares();
    else
      sharesExchanged = sell.getNumShares();
    
    // Update the number of shares the buyer is willing to buy and the seller is willing to sell
    buy.subtractShares(sharesExchanged);
    sell.subtractShares(sharesExchanged);

    if (buy.getNumShares() == 0) // The buyer has bought all the stocks they are willing to buy
    {
      buyOrders.remove(buy); // remove order
      if(buyOrders.peek() != null && !buyOrders.peek().isMarket()) // update market
        bidMarketPrice = buyOrders.peek().getPrice();
    }

    if (sell.getNumShares() == 0) // The seller has sold all the stocks they are willing to sell
    {
      sellOrders.remove(sell); // remove order
      if(sellOrders.peek() != null && !sellOrders.peek().isMarket()) // update market
        askMarketPrice = sellOrders.peek().getPrice();
    }

    // Update the lowest and highest prices, and volume
    if (latestSalePrice < loPrice)
      loPrice = latestSalePrice;

    if (latestSalePrice > hiPrice)
      hiPrice = latestSalePrice;

    volume += sharesExchanged;

    // Send a message to the two traders involved in the exchange
    buyer.receiveMessages("You bought: " + sharesExchanged + " " + stockSymbol + " at "
        + money.format(latestSalePrice) + " amt "
        + money.format(sharesExchanged * latestSalePrice));
    seller.receiveMessages("You sold: " + sharesExchanged + " " + stockSymbol + " at "
        + money.format(latestSalePrice) + " amt "
        + money.format(sharesExchanged * latestSalePrice));

    // Keep recursively calling this method
    executeOrders();
  }
}
