public class TradeOrder
{
  private Trader trader;
  private String symbol;
  private boolean buyOrder, marketOrder;
  private int numShares;
  private double price;
  
  public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares, double price)
  {
    this.trader = trader;
    this.symbol = symbol;
    this.buyOrder = buyOrder;
    this.marketOrder = marketOrder;
    this.numShares = numShares;
    this.price = price;
  }
  
  public Trader getTrader()
  {
    return trader;
  }
  
  public String getSymbol()
  {
    return symbol;
  }
  
  //if false, is selling
  public boolean isBuy()
  {
    return buyOrder;
  }
  
  //if false, is limit price
  public boolean isMarket()
  {
    return marketOrder;
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
