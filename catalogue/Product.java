package catalogue;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Used to hold the following information about
 * a product: Product number, Description, Price and
 * Stock level.
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */

public class Product implements Serializable, Comparator
{
  private static final long serialVersionUID = 20092506;
  private String theProductNum;       // Product number
  private String theDescription;      // Description of product
  private double thePrice;            // Price of product
  private int    theQuantityInStock;  // Quantity involved

  /**
   * Construct a product details
   * @param aProductNum Product number
   * @param aDescription Description of product
   * @param aPrice The price of the product
   * @param aQuantity The Quantity of the product involved
   */
  public Product( String aProductNum, String aDescription,
                  double aPrice, int aQuantity )
  {
    theProductNum  = aProductNum;     // Product number
    theDescription = aDescription;    // Description of product
    thePrice       = aPrice;          // Price of product
    theQuantityInStock    = aQuantity;       // Quantity involved
  }
  
  public String getProductNum()  { return theProductNum; }
  public String getDescription() { return theDescription; }
  public double getPrice()       { return thePrice; }
  public int    getQuantity()    { return theQuantityInStock; }
  
  public void setProductNum( String aProductNum )
  { 
    theProductNum = aProductNum;
  }
  
  public void setDescription( String aDescription )
  { 
    theDescription = aDescription;
  }
  
  public void setPrice( double aPrice )
  { 
    thePrice = aPrice;
  }
  
  public void setQuantity( int aQuantity )
  { 
    theQuantityInStock = aQuantity;
  }

  // check if the product is equal to the other
  public boolean equals(Product other)
  {
	  if(theProductNum != other.getProductNum())
		  return false;
	  else
		  return true;
  }
  
  /**
   *  compare two product's product number
   *  return 0 : numbers are equal, 
   *  		1: arg0's product number is greater than arg1's
   *  		-1: arg0's product number is less than arg1
   */
  @Override
  public int compare(Object arg0, Object arg1) {
	Product pr1 = (Product)arg0;
	Product pr2 = (Product)arg1;
		
	return pr1.getProductNum().compareTo(pr2.getProductNum());
  }

}
