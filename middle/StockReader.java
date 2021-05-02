package middle;

import catalogue.Product;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
  * Interface for read access to the stock list.
  * @author  Mike Smith University of Brighton
  * @version 2.0
  */

public interface StockReader
{

 /**
   * Checks if the product exits in the stock list
   * @param pNum Product nymber
   * @return true if exists otherwise false
   * @throws StockException if issue
   */
  boolean exists(String pNum) throws StockException;
  
  /**
   * Checks if the product amount available in the stock
   * @param pNum Product number
   * @param amount Product amount
   * @return true if available otherwise false
   * @throws StockException if issue
   */
  boolean available(String pNum, int amount) throws StockException;

 /**
  * Returns details about the product in the stock list
  * @param pNum Product nymber
  * @return StockNumber, Description, Price, Quantity
  * @throws StockException if issue
  */

 Product getDetails(String pNum) throws StockException;

 /**
  * Returns products found using name
  * @param pName Product name
  * @return list of products
  * @throws StockException if issue
  */

 ArrayList<Product> findProducts(String pName) throws StockException;


 /**
  * Returns an image of the product in the stock list
  * @param pNum Product nymber
  * @return Image
  * @throws StockException if issue
  */

 Image getImage(String pNum) throws StockException;

 /**
  * Returns product numbers of the Top n products in the stock list
  * @param count Count of Top Seller Products
  * @return List<String>
  * @throws StockException if issue
  */

 List<String> getTopProducts(int count) throws StockException;
}
