/**
 * This class provides a no-args constructor that sets ascending to true 
 * and a constructor with one parameter that dictates if ascending is true 
 * or false.
 * 
 * Dictates the process of comparing two trade orders depending on
 * whether ascending is true or false
 * 
 * CSC630 Period 8 Ms. Litvin
 * Date: March 4, 2021
 * Authors: Katie Wimmer, Sima Shmuylovich, Ryan Mai
*/
import java.util.Comparator;

public class PriceComparator implements Comparator<TradeOrder>
{
  private boolean ascending;
  
  /**
   * Sets ascending to boolean value of the parameter.
   */
  public PriceComparator(boolean order) 
  {
    ascending = order;
  }
  
  /**
   * Default sets ascending to true.
   */
  public PriceComparator() 
  {
    ascending = true;
  }
  
  /**
   * Compares two TradeOrder objects based on their prices. 
   */
  public int compare(TradeOrder one, TradeOrder two)
  {
    if(one.isMarketPrice() && two.isMarketPrice()) //both are the market price
      return 0; //equal
    else if(one.isMarketPrice() && !two.isMarketPrice()) //one is the market price and the other is the limit
      return -1; //less than
    else if(two.isMarketPrice() && !one.isMarketPrice()) //two is the market price and the other is the limit
      return 1; //more than
    else
    {
      if (ascending) // returns it in ascending
        return (int) (one.getPrice() - two.getPrice() * 100);
      else // returns it in descending
        return (int) (-one.getPrice() + (two.getPrice() * 100));
    }
  }
}
