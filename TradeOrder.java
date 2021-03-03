/**
 * This class represents a "data carrier" object used by other objects to pass 
 * the data about a trade order to each other. The fields and accessor methods 
 * correspond to the data entry fields in TraderWindow.java.
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
  
  /**
   * Initializes a TradeOrder with answers to the following questions:
   *    Who is the trader placing this order?
   *    What stock is the order being place on?
   *    Is the trader looking to buy or sell?
   *    Is the order a market order or a limit order?
   *    How many shares does the trader wish to buy/sell?
   *    What price is the trader offering?
   */
  public TradeOrder(Trader trader, String symbol, boolean buyOrder,
      boolean marketOrder, int numShares, double price)
  {
    this.trader = trader;
    this.symbol = symbol;
    this.buyOrder = buyOrder;
    this.marketOrder = marketOrder;
    this.numShares = numShares;
    this.price = price;
  }
  
  /**
   * Returns the trader placing the order. 
   */
  public Trader getTrader()
  {
    return trader;
  }
  
  /**
   * Returns the symbol of the stock the order is being placed on.
   */
  public String getSymbol()
  {
    return symbol;
  }
  
  /**
   * Returns true if the trader wishes to buy, false if they wish to sell. 
   */  
  public boolean isBuying()
  {
    return buyOrder;
  }
  
  /**
   * Returns true if the order is a market order, false if it is a limit order. 
   */  
  public boolean isMarketPrice()
  {
    return marketOrder;
  }
  
  /**
   * Returns the number of shares the trader wishes to buy/sell. 
   */  
  public int getNumShares()
  {
    return numShares;
  }
  
  /**
   * Returns the offered price. 
   */ 
  public double getPrice()
  {
    return price;
  }
}
