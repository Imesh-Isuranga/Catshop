package clients.catalogueDisplay;

import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import middle.MiddleFactory;
import middle.StockReader;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Catalogue view.
 * @author  constantinos
 * @version 1.0
 */

public class CatalogueView implements Observer {
    class Name                              // Names of buttons
    {
        public static final String FIND = "Find";
        public static final String CLEAR = "Clear";
    }

    private static final int H = 300;       // Height of window pixels
    private static final int W = 400;       // Width  of window pixels

    private final Label theAction = new Label();
    private final TextField theInput = new TextField();
    private final TextArea theOutput = new TextArea();
    private final Button theBtFind = new Button(clients.catalogueDisplay.CatalogueView.Name.FIND);
    private final Button theBtClear = new Button(clients.catalogueDisplay.CatalogueView.Name.CLEAR);

    private StockReader theStock = null;
    private CatalogueController cont = null;


    /**
     * Construct the view
     *
     * @param stage Window in which to construct
     * @param mf    Factor to deliver order and stock objects
     * @param x     x-cordinate of position of window on screen
     * @param y     y-cordinate of position of window on screen
     */

    public CatalogueView(Stage stage, MiddleFactory mf, int x, int y) {
        try                                             //
        {
            theStock = mf.makeStockReader();             // Database Access
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        stage.setWidth(W); // Set Window Size
        stage.setHeight(H);
        stage.setX(x);  // Set Window Position
        stage.setY(y);

        theBtFind.setPrefSize(80, 40); // Check Button Size
        theBtFind.setOnAction(event -> cont.doFindProduct(theInput.getText()));

        theBtClear.setPrefSize(80, 40); // Clear Button Size
        theBtClear.setOnAction(event -> cont.doClear());

        theAction.setPrefSize(270, 20);
        theAction.setText("Welcome!");                        //  Blank

        theInput.setPrefSize(270, 40);
        theInput.setText("");                           // Blank

        theOutput.setPrefSize(270, 160);
        theOutput.setText("");                        //  Blank

        GridPane buttonBar = new GridPane();
        buttonBar.addColumn(0, theBtFind, theBtClear);
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

        String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5;" +
                "-fx-border-radius: 5; -fx-border-color: blue; -fx-background-color: #b4fcb4;";
        String buttonStyle = "-fx-background-color: #71fc48; -fx-text-fill: black;";

        root.setStyle(rootStyle);
        theBtClear.setStyle(buttonStyle);
        theBtFind.setStyle(buttonStyle);

        Scene scene = new Scene(root);  // Create the Scene
        stage.setScene(scene); // Add the scene to the Stage

        theInput.requestFocus();  // Focus is here
    }

    /**
     * The controller object, used so that an interaction can be passed to the controller
     *
     * @param c The controller
     */

    public void setController(CatalogueController c) {
        cont = c;
    }

    /**
     * Update the view
     *
     * @param modelC The observed model
     * @param arg    Specific args
     */

    public void update(Observable modelC, Object arg) {
        CatalogueModel model = (CatalogueModel) modelC;
        String message = (String) arg;
        theAction.setText(message);

        theOutput.setText(model.getFindResult());
        theInput.requestFocus();               // Focus is here
    }
}