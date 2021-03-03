/**
Provides a constructor for creating a TradeOrder object with parameters
for the trader, symbol, buying, market price and price
Includes several accessor methods for each object characteristic
Authors: SS, KW, RM
*/
public class TradeOrder
{
  private Trader trader;
  private String stockSymbol;
  private boolean buying, marketPrice;
  private int numShares;
  private double price;
  
  public TradeOrder(Trader t, String s, boolean b, boolean m, int n, double p) //constructor
  {
    trader = t; //sets each parameter to a field in the TradeOrder class
    stockSymbol = s;
    buying = b;
    marketPrice = m;
    price = p;
  }
  
  public Trader getTrader() //accessor method for trader
  {
    return trader;
  }
  
  public String getStockSymbol() //accessor method for stock symbol
  {
    return stockSymbol;
  }
  
  public boolean isBuying()  
  {
    return buying; //if false, is selling
  }
  

  public boolean isMarketPrice()
  {
    return marketPrice;  //if false, is limit price
  }
  
  public int getNumShares() //accessor methods for number of shares
  {
    return numShares;
  }
  
  public double getPrice() //accessor method for price
  {
    return price;
  } 
}
