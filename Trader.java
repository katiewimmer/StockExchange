/**
 * Depicts a trader. Includes a constructer that accepts a brokerage, name, and password.
 * A trader can request quotes and place orders with the brokerage. It also receives
 * mail in the mailbox, and it can tell the TraderWindow to display the mail.
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
        this.brokerage = brokerage;
        this.name = name;
        this.password = password;
        mailbox = new LinkedList<String>();
    }

    /**
     * Gets quote from brokerage
     * @param symbol
     */
    public void getQuote(String symbol) 
    {
        brokerage.getQuote(symbol, this);
    }

    /**
     * Directs brokerage to place an order
     * @param tradeOrder
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

    // Accessors
    public String getName() 
    {
        return name;
    }

    public String getPassword() 
    {
        return password;
    }

    // Mailbox
     /** Receives mail
     * @param mail
     */
    public void getMail(String mail)
    {
        mailbox.add(mail);
        if(window != null)
        {
            while(!mailbox.isEmpty()) 
            {
                window.showMessage(mailbox.remove());
            }
        }
    }

    public boolean hasMail()
    {
        return !mailbox.isEmpty();
    }

    // TraderWindow
    /**
     * Opens TraderWindow for displaying mail
     */
    public void openTraderWindow() 
    {
        window = new TraderWindow(this);
        while(!mailbox.isEmpty()) 
        {
            window.showMessage(mailbox.remove());
        }
    }
    public int compareTo(Trader other)
    {
    	return this.getName().compareToIgnoreCase(other.getName());
    }

}
