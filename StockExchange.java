import java.util.Map;
import java.util.HashMap;


public class StockExchange 
{
	private HashMap<String, Stock> stocks;
	
	public StockExchange()
	{
		stocks = new HashMap<String, Stock>();
	}
	
	public String processQuote(String symbol)
	{
		return stocks.get(symbol); // add .getQuote() method that is written in Stock
	}
	
	public void addStock(String symbol, String name, double price)
	{
		Stock stock = new Stock(symbol, name, price); // will need to match constructor parameters in Stock
		stocks.put(symbol, stock);
	}
	
	public void addOrder(TradeOrder recipient)
	{
		stocks.get(recipient.getStockSymbol()).addOrder(recipient);
	}
}
