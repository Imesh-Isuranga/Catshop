package clients.cashier;

import catalogue.Basket;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockReadWriter;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;


/**
 * View of the model
 * @author  M A Smith (c) June 2014  
 */
public class CashierView implements Observer
{
  private static final int H = 600;       // Height of window pixels
  private static final int W = 800;       // Width  of window pixels
  
  private static final String CHECK  = "Check";
  private static final String BUY    = "Buy";
  private static final String BOUGHT = "Bought";
  private static final String REMOVE = "Remove";
  private static final String DISCOUNT = "Discount";
  
  private final Label      theAction  = new Label();
  private final TextField  theInput   = new TextField();
  private final TextField  theInputDiscount   = new TextField();
  private final TextArea   theOutput  = new TextArea();
  private final Button     theBtCheck = new Button( CHECK );
  private final Button     theBtBuy   = new Button( BUY );
  private final Button     theBtBought= new Button( BOUGHT );
  private final Button     theBtRemove= new Button( REMOVE );
  private final Button 		theBtDiscount = new Button( DISCOUNT );
  
  private StockReadWriter theStock     = null;
  private OrderProcessing theOrder     = null;
  private CashierController cont       = null;
  
  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-coordinate of position of window on screen 
   * @param y     y-coordinate of position of window on screen  
   */
          
  public CashierView(  Stage stage,  MiddleFactory mf, int x, int y  )
  {
    try                                           // 
    {      
      theStock = mf.makeStockReadWriter();        // Database access
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    stage.setWidth( W ); // Set Window Size
    stage.setHeight( H );
    stage.setX( x );  // Set Window Position
    stage.setY( y );

//    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

    theBtCheck.setPrefSize(  100, 40 );    // Check Button
    theBtCheck.setOnAction( event->cont.doCheck( theInput.getText() ) ); // Call back code
    
    theBtDiscount.setPrefSize(  100, 40 );    // Check Button
    theBtDiscount.setOnAction( event->cont.doDiscount(theInputDiscount.getText())); // Call back code

    theBtBuy.setPrefSize(  100, 40 );      // Buy button
    theBtBuy.setOnAction( event -> cont.doBuy() );

    theBtBought.setPrefSize(  100, 40 );   // Clear Button
    theBtBought.setOnAction(                  // Call back code
            event -> cont.doBought() );

    theBtRemove.setPrefSize(  100, 40 );   // Clear Button
    theBtRemove.setOnAction(                  // Call back code
            event -> cont.doRemove() );

    theAction.setPrefSize( 650, 20 );       // Message area
    theAction.setText( "Welcome" );                        // Blank

    theInput.setPrefSize( 650, 40 );         // Input Area
    theInput.setText("");                           // Blank

    theInputDiscount.setPrefSize( 650, 40 );         // Input Area
    theInputDiscount.setText("10.0");                           

    theOutput.setPrefSize( 650, 400 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
//    theOutput.setFont( f );                         //  Uses font

    GridPane buttonPane = new GridPane(); // button Pane
    buttonPane.addColumn(0, theBtCheck, theBtBuy, theBtRemove, theBtBought, theBtDiscount);
    buttonPane.setVgap(30); // Vertical Spacing

    GridPane infoPane = new GridPane();
    infoPane.addColumn(0, theAction, theInput, theOutput, theInputDiscount);
    infoPane.setVgap(10);

    HBox root = new HBox();
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList rootList = root.getChildren(); // retrieving the observable list of the root pane
    rootList.addAll(buttonPane, infoPane); // Adding all the nodes to the observable list


    // Set the Size of the GridPane
    root.setMinSize(800, 600);
    // Set style
    String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5;" +
            "-fx-border-radius: 5; -fx-border-color: purple; -fx-background-color: #b19cd9;";
    String redButtonStyle = "-fx-background-radius: 1em; -fx-background-color: red; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
    String blueButtonStyle = "-fx-background-radius: 1em; -fx-background-color: blue; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
    String brownButtonStyle = "-fx-background-radius: 1em; -fx-background-color: brown; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
    String pinkButtonStyle = "-fx-background-radius: 1em; -fx-background-color: pink; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
    String greyButtonStyle = "-fx-background-radius: 1em; -fx-background-color: grey; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
    String inputStyle = "-fx-background-color:lightgreen; -fx-font-family: Calibri; -fx-font-size: 14px";
    String richAreaStyle = "-fx-control-inner-background:lightgreen; -fx-font-family: Calibri; -fx-font-size: 14px";
    String labelStyle = "-fx-font-family: Calibri; -fx-font-size: 14px; -fx-font-weight: bolder;";

    root.setStyle(rootStyle);
    theBtCheck.setStyle(blueButtonStyle);
    theBtBuy.setStyle(redButtonStyle);
    theBtBought.setStyle(brownButtonStyle);
    theBtRemove.setStyle(greyButtonStyle);
    theBtDiscount.setStyle(pinkButtonStyle);
    theInput.setStyle(inputStyle);
    theInputDiscount.setStyle(inputStyle);
    theOutput.setStyle(richAreaStyle);
    theAction.setStyle(labelStyle);

    Scene scene = new Scene(root);  // Create the Scene
    stage.setScene(scene); // Add the scene to the Stage

    theInput.requestFocus();                        // Focus is here
  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( CashierController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
    CashierModel model  = (CashierModel) modelC;
    String      message = (String) arg;
    Platform.runLater(()->theAction.setText(message));
    Basket basket = model.getBasket();
    if ( basket == null )
    	Platform.runLater(()->theOutput.setText( "Customers order" ));
    else
    	Platform.runLater(()->theOutput.setText( basket.getDetails()));
    
    Platform.runLater(()->theInput.requestFocus());               // Focus is here
  }

}
