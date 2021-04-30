package clients.adDisplay;

import catalogue.Basket;
import catalogue.BetterBasket;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import middle.MiddleFactory;
import middle.StockReader;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Adverts view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class AdvertsView implements Observer
{
  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels
  private static final int UPDATEINTERVAL = 100; // Ads updating interval
  private static final int ADSCOUNT = 3; // Ads count

  private AudioClip theAudioClip;

  private final Label theDescription  = new Label();
  private ImageView thePicture = new ImageView();

  private StockReader theStock   = null;
  private AdvertsController cont= null;


  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  
  public AdvertsView(Stage stage, MiddleFactory mf, int x, int y )
  {
    // Create an AudioClip, which loads the audio data synchronously
    final URL resource = getClass().getResource("/audio/welcome.mp3");
    theAudioClip = new AudioClip(resource.toExternalForm());

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

    thePicture.setFitWidth( 350 );   // Picture area
    thePicture.setFitHeight( 200 );
    thePicture.setPreserveRatio(true);

    theDescription.setPrefSize( 350, 20 );
    theDescription.setText( "" );                        //  Blank

    VBox root = new VBox();
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList rootList = root.getChildren(); //retrieving the observable list of the root pane
    rootList.addAll(thePicture, theDescription); //Adding all the nodes to the observable list


    // Set the Size of the GridPane
    root.setMinSize(700, 500);

    String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5;" +
            "-fx-border-radius: 5; -fx-border-color: blue; -fx-background-color: #b4fcb4;";

    root.setStyle(rootStyle);


    Scene scene = new Scene(root);  // Create the Scene
    stage.setScene(scene); // Add the scene to the Stage
//    theAudioClip.setCycleCount(AudioClip.INDEFINITE);
//    theAudioClip.play();
    Timeline adTimeLine = new Timeline(
            new KeyFrame(Duration.seconds(5),event->showAds()));
    adTimeLine.setCycleCount(Timeline.INDEFINITE);
    adTimeLine.play();
  }

   /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( AdvertsController c )
  {
    cont = c;
  }

  public static int adsIdx = 0;
  public void showAds()
  {
    if(cont == null)
      return;
    // update Ads content in every 500S
    if(adsIdx % UPDATEINTERVAL == 0)
      cont.findTopSellers(ADSCOUNT);

    // show Ads
    String description = cont.getDescription(adsIdx % ADSCOUNT);
    Image image = cont.getPicture(adsIdx % ADSCOUNT);

    thePicture.setImage(image);
    theDescription.setText(description);

    adsIdx++;
  }
  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
   
  public void update( Observable modelC, Object arg )
  {
//    AdvertsModel model  = (AdvertsModel) modelC;
//    String message = (String) arg;
//    theAction.setText( message );
//    Image image = model.getPicture();  // Image of product
//    if ( image == null )
//    {
//      thePicture.setImage(null);                  // Clear picture
//    } else {
//      thePicture.setImage( image );             // Display picture
//    }
//    theOutput.setText( model.getBasket().getDetails() );
//    theInput.requestFocus();               // Focus is here
  }
}
