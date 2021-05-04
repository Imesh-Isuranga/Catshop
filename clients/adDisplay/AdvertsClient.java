package clients.adDisplay;

import javafx.application.Application;
import javafx.stage.Stage;
import middle.Names;
import middle.RemoteMiddleFactory;

/**
 * The standalone Adverts Client
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */
public class AdvertsClient extends Application
{
  public static RemoteMiddleFactory mrf;

  public static void main (String args[])
  {
    String stockURL = args.length < 1         // URL of stock R
                    ? Names.STOCK_R           //  default  location
                    : args[0];                //  supplied location
    
    mrf = new RemoteMiddleFactory();
    mrf.setStockRInfo( stockURL );

    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Advertisement Client (MVC RMI)");

    AdvertsModel model = new AdvertsModel(mrf);
    AdvertsView  view  = new AdvertsView( primaryStage, mrf, 0, 0 );
    AdvertsController cont  = new AdvertsController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    primaryStage.show();
  }
}
