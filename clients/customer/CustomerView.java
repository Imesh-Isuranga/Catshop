package clients.customer;

import catalogue.Basket;
import catalogue.BetterBasket;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import middle.MiddleFactory;
import middle.StockReader;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class CustomerView implements Observer
{
  class Name                              // Names of buttons
  {
    public static final String CHECK  = "Check";
    public static final String CLEAR  = "Clear";
  }

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final Label theAction  = new Label();
  private final TextField  theInput   = new TextField();
  private final TextArea   theOutput  = new TextArea();
  private final Button     theBtCheck = new Button( Name.CHECK );
  private final Button     theBtClear = new Button( Name.CLEAR );

  private ImageView thePicture = new ImageView();

  private StockReader theStock   = null;
  private CustomerController cont= null;

  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  
  public CustomerView(Stage stage, MiddleFactory mf, int x, int y )
  {
    try                                             // 
    {      
      theStock  = mf.makeStockReader();             // Database Access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }

    stage.setWidth( W ); // Set Window Size
    stage.setHeight( H );
    stage.setX( x );  // Set Window Position
    stage.setY( y );

    theBtCheck.setPrefSize( 80, 40 ); // Check Button Size
    theBtCheck.setOnAction(event -> cont.doCheck(theInput.getText()));

    theBtClear.setPrefSize( 80, 40 ); // Clear Button Size
    theBtClear.setOnAction(event -> cont.doClear());

    thePicture.setFitWidth( 80 );   // Picture area
    thePicture.setFitHeight( 80 );

    theAction.setPrefSize( 270, 20 );
    theAction.setText( "Welcome!" );                        //  Blank

    theInput.setPrefSize( 270, 40 );
    theInput.setText("");                           // Blank

    theOutput.setPrefSize( 270, 160 );
    theOutput.setText( "" );                        //  Blank

    GridPane buttonBar = new GridPane();
    buttonBar.addColumn(0, theBtCheck, theBtClear, thePicture);
    buttonBar.setVgap(10); // Set the horizontal spacing to 10px

    GridPane infoBar = new GridPane();
    infoBar.addColumn(0, theAction, theInput, theOutput);
    infoBar.setVgap(10);

    HBox root = new HBox();
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList rootList = root.getChildren(); //retrieving the observable list of the root pane
    rootList.addAll(buttonBar, infoBar); //Adding all the nodes to the observable list


    // Set the Size of the GridPane
    root.setMinSize(700, 500);

    String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5;" +
            "-fx-border-radius: 5; -fx-border-color: blue; -fx-background-color: #b4fcb4;";
    String buttonStyle = "-fx-background-color: #71fc48; -fx-text-fill: black;";

    root.setStyle(rootStyle);
    theBtClear.setStyle(buttonStyle);
    theBtCheck.setStyle(buttonStyle);

    Scene scene = new Scene(root);  // Create the Scene
    stage.setScene(scene); // Add the scene to the Stage

    theInput.requestFocus();  // Focus is here
  }

   /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( CustomerController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
   
  public void update( Observable modelC, Object arg )
  {
    CustomerModel model  = (CustomerModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
//    ImageIcon image = model.getPicture();  // Image of product
//    if ( image == null )
//    {
//      thePicture.clear();                  // Clear picture
//    } else {
//      thePicture.set( image );             // Display picture
//    }
    theOutput.setText( model.getBasket().getDetails() );
    theInput.requestFocus();               // Focus is here
  }

}
