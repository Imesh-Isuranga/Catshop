package clients.collection;

import middle.MiddleFactory;
import middle.OrderProcessing;

import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class CollectView implements Observer
{
    private static final String COLLECT = "Collect";

    private static final int H = 300;       // Height of window pixels
    private static final int W = 400;       // Width  of window pixels

    private final Label      theAction  = new Label();
    private final TextField  theInput   = new TextField();
    private final TextArea   theOutput  = new TextArea();
    private final Button     theBtCollect= new Button( COLLECT );

    private OrderProcessing   theOrder = null;
    private CollectController cont     = null;

    /**
    * Construct the view
    * @param stage   Window in which to construct
    * @param mf    Factor to deliver order and stock objects
    * @param x     x-cordinate of position of window on screen
    * @param y     y-cordinate of position of window on screen
    */
    public CollectView(  Stage stage, MiddleFactory mf, int x, int y )
    {
        try                                           //
        {
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

        theBtCollect.setPrefSize( 80, 40 );  // Check Button
        theBtCollect.setOnAction(                 // Call back code
            event -> cont.doCollect( theInput.getText()) );

        theAction.setPrefSize( 270, 20 );       // Message area
        theAction.setText( "Welcome!" );                        // Blank

        theInput.setPrefSize( 270, 40 );         // Input Area
        theInput.setText("");                           // Blank

        theOutput.setPrefSize( 270, 160 );          // In TextArea
        theOutput.setText( "" );                        //  Blank
//        theOutput.setFont( f );                         //  Uses font

        GridPane buttonPane = new GridPane(); // button Pane
        buttonPane.addColumn(0, theBtCollect );
        buttonPane.setVgap(10); // Vertical Spacing

        GridPane infoPane = new GridPane();
        infoPane.addColumn(0, theAction, theInput, theOutput);
        infoPane.setVgap(10);

        HBox root = new HBox();
        root.setSpacing(10);   //Setting the space between the nodes of a root pane

        ObservableList rootList = root.getChildren(); // retrieving the observable list of the root pane
        rootList.addAll(buttonPane, infoPane); // Adding all the nodes to the observable list


        // Set the Size of the GridPane
        root.setMinSize(700, 500);
        // Set style
        String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5;" +
                "-fx-border-radius: 5; -fx-border-color: blue; -fx-background-color: #b4fcb4;";
        String buttonStyle = "-fx-background-color: #71fc48; -fx-text-fill: black;";

        root.setStyle(rootStyle);
        theBtCollect.setStyle(buttonStyle);

        Scene scene = new Scene(root);  // Create the Scene
        stage.setScene(scene); // Add the scene to the Stage

        theInput.requestFocus();                        // Focus is here
    }

    public void setController( CollectController c )
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
        CollectModel model  = (CollectModel) modelC;
        String        message = (String) arg;
        theAction.setText( message );

        theOutput.setText( model.getResponce() );
        theInput.requestFocus();               // Focus is here
    }

}
