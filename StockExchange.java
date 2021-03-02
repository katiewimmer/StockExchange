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
		return stocks.get(symbol).getQuote();
	}
	
	public void addStock(String symbol, String name, double price)
	{
		Stock stock = new Stock(symbol, name, price);
		stocks.put(symbol, stock);
	}
	
	public void addOrder(TradeOrder recipient)
	{
		stocks.get(recipient.getStockSymbol()).placeOrder(recipient);
	}
}
