/**
 * Keeps a HashMap of listed stocks, keyed by their symbols, 
 * and provides method for quote requests and 
 * trade orders to them. 
 * 
 * CSC630 Period 8 Ms. Litvin
 * Date: March 4, 2021
 * Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
*/

import java.util.Map;
import java.util.HashMap;

public class StockExchange 
{
 private Map<String, Stock> listedStocks; // map of active stocks
 
 /**
  * Initializes a Map of listed stocks.
  */
 public StockExchange() 
 {
   listedStocks = new HashMap<String, Stock>();
 }
 
 /**
  * Returns the quote of the listed stock associated with a given symbol
  */
 public String processQuote(String symbol)
 {
   Stock stock = listedStocks.get(symbol);
   if (stock != null)
     return stock.getQuote(); //returns the quote of the stock
   return symbol + " not found"; // if the symbol is not related to a stock
 }

 /**
  * Creates and adds a new stock with a given symbol, name, and price to the Map of listed stocks.
  */
 public void listStock(String symbol, String name, double price) 
 {
   Stock stock = new Stock(symbol, name, price); //creates new stock
   listedStocks.put(symbol, stock); // adds it to the list
 }
 
 public void addOrder(TradeOrder recipient) //places an order to a given recipient
 {
   listedStocks.get(recipient.getSymbol()).placeOrder(recipient); 
 }
}
