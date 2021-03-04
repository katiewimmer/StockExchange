
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
*/
import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock
{
  //Format prices
  public static DecimalFormat money = new DecimalFormat("0.00");

  private String stockSymbol, companyName;
  private double lowPrice, highPrice, latestPrice;
  private int volume;
  private PriorityQueue<TradeOrder> buyOrders, sellOrders;

  /**
   * 
   */
  public Stock(String symbol, String name, double price)
  {
    stockSymbol = symbol;
    companyName = name;
    lowPrice = price;
    highPrice = price;
    latestPrice = price;
    volume = 0;
    buyOrders = new PriorityQueue<TradeOrder>(new PriceComparator());
    sellOrders = new PriorityQueue<TradeOrder>(new PriceComparator(false));
  }

  public String getQuote()
  {
    String quote = companyName + " (" + stockSymbol + ")\nPrice: " + latestPrice
        + " hi: " + highPrice + " lo: " + lowPrice + " vol:" + volume + " ";

    String ask = "Ask: none";
    String bid = "Bid: none";

    if (!sellOrders.isEmpty())
    {
      ask = "Ask: " + sellOrders.peek().getPrice() + " size: "
          + sellOrders.peek().getNumShares();
    }

    if (!buyOrders.isEmpty())
    {
      bid = "Bid: " + buyOrders.peek().getPrice() + " size: "
          + buyOrders.peek().getNumShares();
    }

    return quote + ask + " " + bid;
  }

  public void placeOrder(TradeOrder order)
  {
    String str = "New order: ";

    if (order.isBuying())
    {
      buyOrders.add(order);
      str += "Buy ";
    }
    else
    {
      sellOrders.add(order);
      str += "Sell ";
    }

    str += order.getSymbol() + " (" + this.companyName + ")" + "\n"
        + order.getNumShares() + " shares at ";

    if (!order.isMarketPrice())
      str += order.getPrice();
    else
      str += "market";

    order.getTrader().getMail(str);
    executeOrders();
  }

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
    double price;

    if (!(buy.isMarketPrice() || sell.isMarketPrice()) && buy.getPrice() >= sell.getPrice())
      price = sell.getPrice();
    else if (!buy.isMarketPrice() && sell.isMarketPrice())
      price = buy.getPrice(); // use buyer's price
    else if (buy.isMarketPrice() && !sell.isMarketPrice())
      price = sell.getPrice(); // seller's price
    else if (buy.isMarketPrice() && sell.isMarketPrice())
      price = latestPrice; // use latest market price.
    else
      return; // sell price > buy price, does nothing

    // Calculate the number of shares being exchanged
    int sharesExchanged;

    if (buy.getNumShares() <= sell.getNumShares())
      sharesExchanged = buy.getNumShares();
    else
      sharesExchanged = sell.getNumShares();

    if (buy.getNumShares() == 0) // The buyer has bought all the stocks they are willing to buy
      buyOrders.remove(buy);

    if (sell.getNumShares() == 0) // The seller has sold all the stocks they are willing to sell
      sellOrders.remove(sell);

    // Update the lowest and highest prices, market price, and volume
    if (price < lowPrice)
      lowPrice = price;

    if (price > highPrice)
      highPrice = price;

    volume += sharesExchanged;
    latestPrice = price;

    // Send a message to the two traders involved in the exchange
    buyer.getMail("You bought: " + sharesExchanged + " " + stockSymbol + " at "
        + money.format(price) + " amt "
        + money.format(sharesExchanged * price));
    seller.getMail("You sold: " + sharesExchanged + " " + stockSymbol + " at "
        + money.format(price) + " amt "
        + money.format(sharesExchanged * price));

    // Keep recursively calling this method
    executeOrders();
  }
}
