/**
* Provides a constructor for creating a TradeOrder,
* several accessor methods, and a mutator method
*
* CSC630 Period 8 Ms. Litvin
* Date: March 4, 2021
* Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
*/

public class TradeOrder
{
  private Trader trader;
  private String symbol;
  private boolean buyOrder, marketOrder;
  private int numShares;
  private double price;
  
  public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares, double price)
  {
    this.trader = trader; //trader associated with the order
    this.symbol = symbol; // symbol for the order
    this.buyOrder = buyOrder; // whether or not the trader is buying or selling
    this.marketOrder = marketOrder; // whether or not the price is the market or limit price
    this.numShares = numShares; // number of shares in the order
    this.price = price; // price at which each stock is being sold
  }
  
  public Trader getTrader() // accessor method for trader
  {
    return trader;
  }
  
  public String getSymbol() // accessor method for symbol
  {
    return symbol;
  }
  
  public boolean isBuy() // if true, the trader is buying, if false, the trader is selling
  {
    return buyOrder;
  }
  
  public boolean isMarket()// if true, the price is the market price, if false, the price is the limit price
  {
    return marketOrder;
  }
  
  public int getNumShares() // accessor method for number of share
  {
    return numShares;
  }
  
  public double getPrice() // accessor method for price
  {
    return price;
  } 
  
  public void subtractShares(int shares) // mutator method to substract a certain number of shares from the total
  {
    numShares -= shares;
  }
}
