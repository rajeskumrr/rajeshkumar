//Package
package Controller;

//Import Statements
import DAO.SignInDAO;
import Model.DetailsModel;
import Model.SignInModel;
import com.sun.scenario.effect.impl.Renderer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 * In this controller class the registered user can login and navigate through the homepage.
 */
public class SignInController implements Initializable {

    @FXML
    private Button back;
    
    @FXML
    private ComboBox userType;
    
    ObservableList<String> mainuserTypeList = FXCollections.observableArrayList("Admin","Patient","Doctor","Lab Technician");
    
    @FXML
    private AnchorPane pane1;
   
    
    @FXML
    private Button login;
    
    @FXML
    private TextField emailid;
    
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userType.setItems(mainuserTypeList);
        userType.setValue("Admin");
    }    

    /**
     * On back button, it navigates to the main registeration page.
     * @param event
     * @throws IOException 
     */ 
    @FXML
    private void onBack(ActionEvent event) throws IOException 
    {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Registration.fxml"));
                      pane1.getChildren().setAll(pane);   
    }

    
    /**
     * When respective users log in it checks if the email id and password is in the database and then logs in.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void OnLogin(ActionEvent event) throws IOException 
    {
        String registration_details_mail = emailid.getText();
        String registration_details_password = password.getText();
        
        
        
        SignInModel objSignInModel =new   SignInModel();
        objSignInModel.setUserType((String) userType.getSelectionModel().getSelectedItem());
        String registration_details_usertype = objSignInModel.getUserType();
                              
        
                SignInDAO objSignInDAO = new SignInDAO();
     int i = objSignInDAO.dataBase(registration_details_usertype, 
             registration_details_mail, registration_details_password);
     

            if(i == 1)
            {                
                  Image img = new Image("/image/small_tick2.png") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Success")
                                  .text("Login Successful")
                                  .graphic(new ImageView(img))
                                  //.graphic(null)
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle(); 
                                  notificationBuilder.show();    
                    
                                   

                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
                    pane1.getChildren().setAll(pane);

                 Model.SignInModel objUserEmail = new SignInModel();
                 objUserEmail.setFn(registration_details_mail);
objSignInModel.setUserType((String) userType.getSelectionModel().getSelectedItem());
                
            }   else
            {
                            Image img = new Image("/image/fail.jpg") ;   
                                Notifications notificationBuilder = Notifications.create()
                                  .title("Error")
                                  .text("Login Failed.Please try Again")
                                  .graphic(new ImageView(img))
                                  //.graphic(null)
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();   
                                  clearFields();
            }
           
            
    }
    
    /**
     * Clear  Fields
     */
    public void clearFields()
    {       
        emailid.clear();
        password.clear(); 
    }
    
}

