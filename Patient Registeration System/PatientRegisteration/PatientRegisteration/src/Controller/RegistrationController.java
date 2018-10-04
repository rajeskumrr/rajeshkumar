//Package
package Controller;

//Import statements
import DAO.RegistrationDAO;
import Model.RegistrationModel;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;



/**
 *In  the RegistrationController, the registration of the user takes place. 
 * There are different buttons, textfields and actions to save the data in respective tables.
 */
public class RegistrationController implements Initializable {
    
    @FXML
    private javafx.scene.control.TextField firstname;
    
    @FXML
    private javafx.scene.control.TextField lastname;
    
    @FXML
    private javafx.scene.control.TextField emailid;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private DatePicker dob;
    
    @FXML
    private ComboBox userType;
    
    @FXML
    private Button SignUp;
    
    @FXML
    private Button Login;
    
    @FXML
    private AnchorPane rootPane;
    
     
   ObservableList<String> mainuserTypeList = FXCollections.observableArrayList("Admin","Patient","Doctor","Lab Technician");
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       userType.setItems(mainuserTypeList);
     dob.setValue(LocalDate.now());
     userType.setValue("Admin");
    }

    /**
     * On Sign up, an action is triggered.
     * This action captures the data in the respective table.
     * @param event 
     */
    @FXML
    private void signupButtonAction(ActionEvent event) 
    {
        if (validateEmail())
        {
        RegistrationModel objRegistrationModel =new   RegistrationModel();
        objRegistrationModel.setFn(firstname.getText());
        objRegistrationModel.setLn(lastname.getText());
        objRegistrationModel.setUserType((String) userType.getSelectionModel().getSelectedItem());
        objRegistrationModel.setMail(emailid.getText());
        objRegistrationModel.setPass(password.getText());
        String registration_details_dob ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = dob.getValue();

        registration_details_dob=(formatter.format(date));
    
        RegistrationDAO objRegistrationDAO =new   RegistrationDAO();
        objRegistrationDAO.dataBase(objRegistrationModel.getFn(),objRegistrationModel.getLn(),objRegistrationModel.getUserType(),
                objRegistrationModel.getMail(),registration_details_dob,objRegistrationModel.getPass());
        clearFields();
        }
       
    }
    
    /**
     * ClearFields
     */
    public void clearFields()
    {
        firstname.clear();
        lastname.clear();
        emailid.clear();
        password.clear(); 
    }
    
    /**
     * On Sign in, it takes the user to the Sign In page.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void login1(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/SignIn.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }
    
    /**
     * This method validates if the patter of the entered emailid  is correct.
     * @return 
     */
    private boolean validateEmail()
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailid.getText());
        if(m.find() && m.group().equals(emailid.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();
            return false;
        }
    }
    
}
    
