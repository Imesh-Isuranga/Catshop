package clients.backDoor;

import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import middle.MiddleFactory;
import middle.StockReadWriter;
import middle.StockReader;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class BackDoorView implements Observer
{
  private static final String RESTOCK  = "Add";
  private static final String CLEAR    = "Clear";
  private static final String QUERY    = "Query";
 
  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final Label      theAction  = new Label();
  private final TextField  theInput   = new TextField();
  private final TextField  theInputNo = new TextField();
  private final TextArea   theOutput  = new TextArea();
  private final Button     theBtClear = new Button( CLEAR );
  private final Button     theBtRStock = new Button( RESTOCK );
  private final Button     theBtQuery = new Button( QUERY );
  
  private StockReadWriter theStock     = null;
  private BackDoorController cont= null;

  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  public BackDoorView(  Stage stage, MiddleFactory mf, int x, int y )
  {
    try                                             // 
    {      
      theStock = mf.makeStockReadWriter();          // Database access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    stage.setWidth( W ); // Set Window Size
    stage.setHeight( H );
    stage.setX( x );  // Set Window Position
    stage.setY( y );
    
//    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

    theBtQuery.setPrefSize( 80, 40 ); // Buy button
    theBtQuery.setOnAction(event->cont.doQuery( theInput.getText() ) ); // Call back code

    theBtRStock.setPrefSize( 80, 40 ); // Check Button
    theBtRStock.setOnAction(event->cont.doRStock( theInput.getText(),
            theInputNo.getText() ) ); // Call back code

    theBtClear.setPrefSize( 80, 40 ); // Clear button
    theBtClear.setOnAction(event->cont.doClear() ); // Call back code


    theAction.setPrefSize( 270, 20 ); // Message area
    theAction.setText( "Welcome!" );                        // Blank

    theInput.setPrefSize( 120, 40 ); // Input Area
    theInput.setText("");                           // Blank

    theInputNo.setPrefSize( 120, 40 ); // Input Area
    theInputNo.setText("0");                        // 0

    theOutput.setPrefSize( 270, 160 ); // Output text area
    theOutput.setText( "" );                        //  Blank

    GridPane buttonPane = new GridPane(); // button Pane
    buttonPane.addColumn(0, theBtQuery, theBtRStock, theBtClear);
    buttonPane.setVgap(10); // Vertical Spacing

    GridPane inputPane = new GridPane();
    inputPane.addRow(0, theInput, theInputNo);
    inputPane.setHgap(20);

    GridPane infoPane = new GridPane();
    infoPane.addColumn(0, theAction, inputPane, theOutput);
    infoPane.setVgap(10);

    HBox root = new HBox();
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList rootList = root.getChildren(); // retrieving the observable list of the root pane
    rootList.addAll(buttonPane, infoPane); // Adding all the nodes to the observable list


    // Set the Size of the GridPane
    root.setMinSize(700, 500);
    // Set style
    String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5;" +
            "-fx-border-radius: 5; -fx-border-color: blue; -fx-background-color: #b4fcb4;";
    String buttonStyle = "-fx-background-color: #71fc48; -fx-text-fill: black;";

    root.setStyle(rootStyle);
    theBtQuery.setStyle(buttonStyle);
    theBtRStock.setStyle(buttonStyle);
    theBtClear.setStyle(buttonStyle);

    Scene scene = new Scene(root);  // Create the Scene
    stage.setScene(scene); // Add the scene to the Stage

    theInput.requestFocus();                        // Focus is here
  }
  
  public void setController( BackDoorController c )
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
    BackDoorModel model  = (BackDoorModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
    
    theOutput.setText( model.getBasket().getDetails() );
    theInput.requestFocus();
  }

}