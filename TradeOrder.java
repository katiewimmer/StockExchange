public class TradeOrder
{
  private Trader trader;
  private String stockSymbol;
  private boolean buying, marketPrice;
  private int numShares;
  private double price;
  
  public TradeOrder(Trader t, String s, boolean b, boolean m, int n, double p)
  {
    trader = t;
    stockSymbol = s;
    buying = b;
    marketPrice = m;
    numShares = n;
    price = p;
  }
  
  public Trader getTrader()
  {
    return trader;
  }
  
  public String getStockSymbol()
  {
    return stockSymbol;
  }
  
  //if false, is selling
  public boolean isBuying()
  {
    return buying;
  }
  
  //if false, is limit price
  public boolean isMarketPrice()
  {
    return marketPrice;
  }
  
  public int getNumShares()
  {
    return numShares;
  }
  
  public double getPrice()
  {
    return price;
  } 
  
  public void subtractShares(int shares)
  {
    numShares -= shares;
  }
}
