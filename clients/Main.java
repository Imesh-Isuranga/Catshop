package clients;
import clients.backDoor.BackDoorController;
import clients.backDoor.BackDoorModel;
import clients.backDoor.BackDoorView;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import clients.collection.CollectController;
import clients.collection.CollectModel;
import clients.collection.CollectView;
import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
import clients.shopDisplay.DisplayController;
import clients.shopDisplay.DisplayModel;
import clients.shopDisplay.DisplayView;
import clients.warehousePick.PickController;
import clients.warehousePick.PickModel;
import clients.warehousePick.PickView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;
import java.awt.*;


/**
 * Starts all the clients  as a single application.
 * Good for testing the system using a single application but no use of RMI.
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */
public class Main extends Application
{
  // Change to false to reduce the number of duplicated clients

  private final static boolean many = false;        // Many clients? (Or minimal clients)

  public static void main (String args[])
  {
    launch(args);
  }

  public void startCustomerGUI_MVC(MiddleFactory mlf )
  {
    Stage stage = new Stage();
    stage.setTitle("Customer Client MVC");
//    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//    stage.setOnCloseRequest(event -> {Platform.exit();});
    Dimension pos = PosOnScrn.getPos();

    CustomerModel model      = new CustomerModel(mlf);
    CustomerView view        = new CustomerView( stage, mlf, pos.width, pos.height );
    CustomerController cont  = new CustomerController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    stage.show();
  }

  /**
   * start the cashier client
   * @param mlf A factory to create objects to access the stock list
   */
  public void startCashierGUI_MVC(MiddleFactory mlf )
  {
    Stage stage = new Stage();
    stage.setTitle( "Cashier Client MVC" );
//    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//    stage.setOnCloseRequest(event -> {Platform.exit();});
    Dimension pos = PosOnScrn.getPos();

    CashierModel model      = new CashierModel(mlf);
    CashierView view        = new CashierView( stage, mlf, pos.width, pos.height );
    CashierController cont  = new CashierController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    stage.show();
  }

  public void startBackDoorGUI_MVC(MiddleFactory mlf )
  {
    Stage stage = new Stage();
    stage.setTitle( "BackDoor Client MVC" );
//    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//    stage.setOnCloseRequest(event -> {Platform.exit();});
    Dimension pos = PosOnScrn.getPos();

    BackDoorModel model      = new BackDoorModel(mlf);
    BackDoorView view        = new BackDoorView( stage, mlf, pos.width, pos.height );
    BackDoorController cont  = new BackDoorController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    stage.show();
  }

  public void startPickGUI_MVC(MiddleFactory mlf )
  {
    Stage stage = new Stage();
    stage.setTitle( "Pick Client MVC" );
//    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//    stage.setOnCloseRequest(event -> {Platform.exit();});
    Dimension pos = PosOnScrn.getPos();

    PickModel model      = new PickModel(mlf);
    PickView view        = new PickView( stage, mlf, pos.width, pos.height );
    PickController cont  = new PickController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    stage.show();
  }

  public void startDisplayGUI_MVC(MiddleFactory mlf )
  {
    JFrame  window = new JFrame();

    window.setTitle( "Display Client MVC");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();

    DisplayModel model      = new DisplayModel(mlf);
    DisplayView view        = new DisplayView( window, mlf, pos.width, pos.height );
    DisplayController cont  = new DisplayController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Make window visible
  }


  public void startCollectionGUI_MVC(MiddleFactory mlf )
  {
    Stage stage = new Stage();
    stage.setTitle( "Collect Client MVC" );
//    stage.setOnCloseRequest(event -> {Platform.exit();});
//    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    Dimension pos = PosOnScrn.getPos();

    CollectModel model      = new CollectModel(mlf);
    CollectView view        = new CollectView( stage, mlf, pos.width, pos.height );
    CollectController cont  = new CollectController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    stage.show();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    MiddleFactory mlf = new LocalMiddleFactory();  // Direct access

    startCustomerGUI_MVC( mlf );
    if ( many )
     startCustomerGUI_MVC( mlf );
    startCashierGUI_MVC( mlf );
    startBackDoorGUI_MVC( mlf );
    if ( many )
      startPickGUI_MVC( mlf );
    startPickGUI_MVC( mlf );
//    startDisplayGUI_MVC( mlf );
//    if ( many )
//      startDisplayGUI_MVC( mlf );
    startCollectionGUI_MVC( mlf );
  }

  @Override
  public void stop(){
    Platform.exit();
  }
}
