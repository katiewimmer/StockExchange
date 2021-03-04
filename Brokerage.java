/**
 * Class to resember a brokerage. Implements a TreeMap of all
 * registered users and a TreeSet of all users that are logged
 * in. Processes quote requests and trade orders, relaying them
 * to traders and the StockExchange
 * 
 * CSC630 Period 8 Ms. Litvin
 * Date: March 4, 2021
 * Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
 */

import java.util.*;

public class Brokerage implements Login 
{

    private TreeMap<String, Trader> traders; //registered users
    private TreeSet<Trader> loggedInTraders; //logged in users
    private StockExchange stockExchange; //

    /**
     * Constructor for Brokerage
     * @param stockExchange
     */
    public Brokerage(StockExchange stockExchange) //constructor
    {
        traders = new TreeMap<String, Trader>(); //initalizes the TreeMap
        loggedInTraders = new TreeSet<Trader>(); //initalizes the TreeSet
        this.stockExchange = stockExchange; //sets stockExchange field to the given parameter 
    }

    /**
     * produces a quote for a trader that relates
     * to a particular symbol
     */
    public void getQuote(String symbol, Trader trader) //takes a symbol and a trader
    {
        trader.receiveMessages(stockExchange.processQuote(symbol));
    }

    /**
     * Relays a tradeOrder to StockExchange
     */
    public void placeOrder(TradeOrder tradeOrder) 
    {
        stockExchange.addOrder(tradeOrder);
    }


    /**
     * verifies if a name and password matches conditions from
     * Login.java. Adds user to the registered traders TreeMap if conditions
     * are met and returns 0. Will return a negative integer if the
     * conditions are not met
     */
    public int addUser(String name, String password) 
    {
        Trader nextTrader = new Trader(this, name, password);  // Login authorization
        if(!(name.length() >= 4 && name.length() <= 10)) //condition #1
            return -1;
        if(!(password.length() >= 2 && password.length() <=10)) //condition #2
            return -2;
        if(traders.containsKey(name)) //condition #3
            return -3;
        traders.put(name, nextTrader); // adds new user
        return 0;

    }

    /**
     * verfies if the inputted name and password match records in
     * the registered traders TreeMap. Adds the trader to loggedInTradders
     * TreeSet if the trader is registered and not already logged in and 
     * returns 0, will return a negative integer if the name and password
     * are not in the TreeMap or if the user is already logged in 
     */
    public int login(String name, String password)
    {
        Trader trader = traders.get(name);
        if(!traders.containsKey(name)) // name is not found in TreeMap
            return -1;
        if(!traders.get( name ).getPassword().equals(password)) // inputted password does not match password associated with given name in the TreeMap
            return -2;
        if(loggedInTraders.contains(traders.get(name))) // user is already logged in
            return -3;
        trader.openTraderWindow();
        if(!trader.receiveMessages())
            trader.receiveMessages("Welcome to SafeTrade!"); //welcome message
        loggedInTraders.add(trader); //adds user to TreeSet of logged in users
        return 0;
    }

    /**
     * Removes a trader from the loggedInTraders TreeSet
     */
    public void logout(Trader trader)
    {
        loggedInTraders.remove(trader); //removed given trader from the TreeSet
    }
}
