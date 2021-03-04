/**
 * Provides a constructer for a Trader that accepts a brokerage, name, and password,
 * accessor methods to request quotes and place orders with the brokerage.
 * Provides methods for the mailbox and call to TraderWindow to display mail
 *
 * CSC630 Period 8 Ms. Litvin
 * Date: March 4, 2021
 * Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
 */

import java.util.*;

public class Trader implements Comparable<Trader>
{

    private Brokerage brokerage;
    private String name, password;
    private TraderWindow window;
    private Queue<String> mailbox;

    public Trader(Brokerage brokerage, String name, String password) 
    {
        this.brokerage = brokerage; // sets to particular brokerage object
        this.name = name; // sets to name of user
        this.password = password; // sets to password of user
        mailbox = new LinkedList<String>(); //initalizes the mailbox as a LinkedList
    }

    /**
     * Returns brokerage for a 
     * a given symbol
     */
    public void getQuote(String symbol)
    {
        brokerage.getQuote(symbol, this);
    }

    /**
     * Directs brokerage to place a tradeOrder
     */
    public void placeOrder(TradeOrder tradeOrder) 
    {
        brokerage.placeOrder(tradeOrder);
    }

    /**
     * Quits the program and logs out as a trader
     */
    public void quit()
    {
        brokerage.logout(this);
    }


    public String getName() //acccessor method for name
    {
        return name;
    }

    public String getPassword() //accessor method for password
    {
        return password;
    }

     /** 
     * adds a messafe to a user's mailbox and
     * displays all messages in a user's mailbox
     */
    public void receiveMessages(String mail)
    {
        mailbox.add(mail); //adds mail to the mailbox
        if(window != null)
        {
            while(!mailbox.isEmpty()) //traverses through mailbox
            {
                window.showMessage(mailbox.remove()); //displays each message
            }
        }
    }
    
    /**
    * Returns true is the mailbox has at least one message, false otherwise
    */
    public boolean receiveMessages()
    {
        return !mailbox.isEmpty();
    }

    /**
     * Initializes TraderWindow for displaying mail
     */
    public void openTraderWindow() 
    {
        window = new TraderWindow(this);
        while(!mailbox.isEmpty()) //traverses mailbox
        {
            window.showMessage(mailbox.remove()); //displays each message
        }
    }
    /**
    * overloaded compareTo method fro two Trader objects
    */
    public int compareTo(Trader other)
    {
      return this.getName().compareToIgnoreCase(other.getName());
    }

}
