/**
 * Class that resembles a brokerage. Brokerage has a TreeMap
 * of all registered users and a TreeSet of logged-in users.
 * Receives quote requests and trade orders from traders and
 * relays them to StockExchange.
 * 
 * CSC630 Period 8 Ms. Litvin
 * Date: March 4, 2021
 * Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
 */

import java.util.*;

public class Brokerage implements Login 
{

    private TreeMap<String, Trader> traders;
    private TreeSet<Trader> loggedInTraders;
    private StockExchange stockExchange;

    /**
     * Constructor for Brokerage
     * @param stockExchange
     */
    public Brokerage(StockExchange stockExchange) 
    {
        traders = new TreeMap<String, Trader>();
        loggedInTraders = new TreeSet<Trader>();
        this.stockExchange = stockExchange;
    }

    /**
     * Gets quote for a specific stock based on inputted symbol
     * @param symbol
     * @param trader
     */
    public void getQuote(String symbol, Trader trader) 
    {
        trader.getMail(stockExchange.processQuote(symbol));
    }

    /**
     * Relays TradeOrder to StockExchange
     * @param tradeOrder
     */
    public void placeOrder(TradeOrder tradeOrder) 
    {
        stockExchange.addOrder(tradeOrder);
    }

    // Login authorization

    /**
     * Checks if an inputted name and password matches conditions from
     * Login.java. Adds user to the registered traders TreeMap if conditions
     * are met. 
     * @param name
     * @param password
     * @return int
     */
    public int addUser(String name, String password) 
    {
        Trader nextTrader = new Trader(this, name, password);
        if(!(name.length() >= 4 && name.length() <= 10))
            return -1;
        if(!(password.length() >= 2 && password.length() <=10))
            return -2;
        if(traders.containsKey(name))
            return -3;
        traders.put(name, nextTrader);
        return 0;

    }

    /**
     * Checks if the inputted name and password match records in
     * the registered traders TreeMap. Adds the trader to loggedInTradders
     * TreeSet if the trader is registered and not already logged in.
     * @param name
     * @param password
     * @return int
     */
    public int login(String name, String password)
    {
        Trader trader = traders.get(name);
        if(!traders.containsKey(name))
            return -1;
        if(!traders.get( name ).getPassword().equals(password))
            return -2;
        if(loggedInTraders.contains(traders.get(name))) 
            return -3;
        trader.openTraderWindow();
        if(!trader.hasMail())
            trader.getMail("Welcome to SafeTrade!");
        loggedInTraders.add(trader);
        return 0;
    }

    /**
     * Removes a trader from the loggedInTraders TreeSet
     * @param trader
     */
    public void logout(Trader trader)
    {
        loggedInTraders.remove(trader);
    }
}
