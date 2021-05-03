package middle;

import catalogue.Product;

/**
  * Interface for read/write access to the stock list.
  * @author  Mike Smith University of Brighton
  * @version 2.0
  */
 
public interface StockReadWriter extends StockReader
{
 /**
   * Customer buys stock,
   * stock level is thus decremented by amount bought.
   * @param pNum Product number
   * @param amount Quantity of product
   * @return StockNumber, Description, Price, Quantity
   * @throws middle.StockException if issue
   */
  boolean buyStock(String pNum, int amount) throws StockException;

  /**
   * Adds stock (Restocks) to store.
   * @param pNum Product number
   * @param amount Quantity of product
   * @throws middle.StockException if issue
   */
  void addStock(String pNum, int amount) throws StockException;
  
  /**
   * Adds reservation to store.
   * @param name Customer Name
   * @return reservation number
   * @throws middle.StockException if issue
   */
  int addReservation(String name) throws StockException;
  
  /**
   * Adds reserved product to store.
   * @param rNum reservation number
   * @param pNum product number
   * @param amount amount of product
   * @throws middle.StockException if issue
   */
  void addReservedProduct(int rNum, String pNum, int amount) throws StockException;
  
  /**
   * remove reservation
   * @param rNum reservation number
   * @throws middle.StockException if issue
   */
  void removeReservation(int rNum) throws StockException;
  
  /**
   * Modifies Stock details for a given product number.
   * Information modified: Description, Price
   * @param detail Replace with this version of product
   * @throws middle.StockException if issue
   */
  void modifyStock(Product detail) throws StockException;

}
