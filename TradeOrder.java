
public class TradeOrder
{
  private Trader trader;
  private String stockSymbol;
  private boolean buyingOrder, marketPrice;
  private int numShares;
  private double price;
  
  public TradeOrder(Trader t, String s, boolean b, boolean m, int n, double p)
  {
    trader = t;
    stockSymbol = s;
    buyingOrder = b;
    marketPrice = m;
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
    return buyingOrder;
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
}
