/**
Provides a no-args constructor that sets ascending to true and a constructor
with one parameter that dictates if ascending is true or false
Dictates the process of comparing two trade orders depending on
whether ascending is true or false
Authors: KW, SS, RM
*/
import java.util.Comparator;

public class PriceComparator implements Comparator<TradeOrder>
{
  private boolean ascending;
  
  public PriceComparator(boolean order) // sets ascending to boolean value of the parameter
  {
    ascending = order;
  }
  
  public PriceComparator() //default sets ascending to true
  {
    ascending = true;
  }
  
  public int compare(TradeOrder one, TradeOrder two)
  {
    if(one.isMarket() && two.isMarket()) //both are the market price
      return 0; //equal
    
    if(one.isMarket() && !two.isMarket()) //one is the market price and the other is the limit
      return -1; //less than
    
    if(two.isMarket() && !one.isMarket()) //two is the market price and the other is the limit
    {
      return 1; //more than
    }
    
    if(ascending) //returns it in ascending
      return (int) (one.getPrice() - two.getPrice() * 100);
    else //returns it in descending
      return (int) (-one.getPrice() + (two.getPrice() * 100)); //
  }
  
  
}
