/**
Provides a no-args constructor for adding a stock to the list of stocks
and several methods for interacting with the stocks in the list of
stocks
Authors: KW, SS, RM
*/
import java.util.Map;
import java.util.HashMap;


public class StockExchange 
{
	private HashMap<String, Stock> stocks;
	
	public StockExchange() //initializes a new stock to stocks list
	{
		stocks = new HashMap<String, Stock>();
	}
	 
	public String processQuote(String symbol) //returns the quote of the stock associated with a certain symbol
	{
		return stocks.get(symbol).getQuote();
	}
	
	public void addStock(String symbol, String name, double price) //adds a new stock with the given parameters to the list of stocks
	{
		Stock stock = new Stock(symbol, name, price); //creates new stock
		stocks.put(symbol, stock); //adds it to the list
	}
	
	public void addOrder(TradeOrder recipient) //places an order to a given recipient
	{
		stocks.get(recipient.getStockSymbol()).placeOrder(recipient); 
	}
}
