//
package Controller;

import Model.SignInModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *This class contains all the Buttons,TextFields and actions.
 */
public class HomePageController implements Initializable {

    @FXML
    private Pane pane1;
        @FXML
    private Pane spane2;
    @FXML
    private Button details;
    @FXML
    private Button messages;
    @FXML
    private Button app;
    @FXML
    private Button reports;
    @FXML
    private Button admin;
    @FXML
    private Button hosp;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane apane1;
    @FXML
    private ScrollPane scpane1;
    @FXML
    private Button logout;
    @FXML
    private Button result;

   
    /**
     * On click of details button will navigate to Details screen.
     * @param event
     * @throws IOException 
     */
    @FXML
     public void ondetails(ActionEvent event) throws IOException 
    {
        
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/Details.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }
    
     /**
      * On click of hospital button will navigate to Map.
      * @param event
      * @throws IOException 
      */
    @FXML
       public void onhosp(ActionEvent event) throws IOException 
    {
       
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/WebUI.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }
     
     /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       SignInModel objSignInModel = new SignInModel();
       objSignInModel.getUserType();
       
       if (objSignInModel.getUserType().equals("1"))// admin
       {
           messages.setVisible(false);
           app.setVisible(false);
           reports.setVisible(false);
           admin.setVisible(true);
           admin.setLayoutX(27.0);
           admin.setLayoutY(127.0);
       }
       else if (objSignInModel.getUserType().equals("2"))// patient
       {
           hosp.setVisible(true);
       }
       else if (objSignInModel.getUserType().equals("3"))// doctor
       {
           reports.setVisible(false);
       }
       else if (objSignInModel.getUserType().equals("4"))// Lab
       {
       messages.setVisible(false);
           app.setVisible(false);
           result.setVisible(true);
           reports.setLayoutX(27.0);
           reports.setLayoutY(127.0);
           result.setLayoutX(27.0);
           result.setLayoutY(231.0);
           
       }
    }    

    /**
     * On click of admin button will navigate to Admin details.
     * @param event
     * @throws IOException 
     */
   @FXML
    private void onadmin(ActionEvent event) throws IOException 
    {
      try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }
    
    /**
     * On click of message button will navigate to Messages Screen.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onmessages(ActionEvent event) throws IOException 
    {
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/Messages.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }
    
    /**
     * On click of appointment button will navigate to Appointment Screen.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onappnt(ActionEvent event) throws IOException 
    {
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }

    /**
     * On click of reports button will navigate to Report Screen.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onreports(ActionEvent event)        throws IOException 
    {
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }

    
    /**
     * On click of history button will navigate to Patient History Screen.
     * @param event
     * @throws IOException 
     */
    private void onhistory(ActionEvent event) throws IOException 
    {
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }
    
    /**
     * On click of Logout button will navigate to Sign In screen.
     * @param event 
     */
    @FXML
    private void onlogout(ActionEvent event) {
        
        try {
            Image img = new Image("/image/small_tick2.png") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Success")
                                  .text("Logged Out Successfully")
                                  .graphic(new ImageView(img))
                                  //.graphic(null)
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle(); 
                                  notificationBuilder.show();  
            
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/SignIn.fxml"));
            apane1.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
        
    }
    
    
    
     @FXML
    private void onresult(ActionEvent event)  {
          try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("/View/LabRecords.fxml"));
            spane2.getChildren().setAll(pane);
            
        } catch (IOException ex) {
        System.out.print(ex);
        }
    }
    
    
    
}
