//Package
package Controller;

//Import statements
import DAO.DetailsDAO;
import Model.DetailsModel;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML controller class.
 * DetailsController class will have buttons ,fields and actions.
 * All the details are displayed.
 */
public class DetailsController implements Initializable {

    
    @FXML
    private DatePicker dob;
    @FXML
    private TextField fullname;
    @FXML
    private TextField address;
    @FXML
    private TextField contact;
    @FXML
    private Button save;
    
    @FXML
    private ComboBox gender;
    
    ObservableList<String> maingender = FXCollections.observableArrayList("Male","Female");
    
    @FXML
    private ComboBox status;
    
    ObservableList<String> mainstatus = FXCollections.observableArrayList("Active","In-Active");
    @FXML
    private Button back1;
    @FXML
    private AnchorPane pane2;
     @FXML
    private Label lblId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.setItems(maingender);
        DetailsDAO objDetailsDAO = new DetailsDAO();
        objDetailsDAO.pageLoad();
        fullname.setText(objDetailsDAO.name);
        address.setText(objDetailsDAO.details_address);
        contact.setText(objDetailsDAO.details_contact);
       gender.getSelectionModel().select(objDetailsDAO.details_gender);
       if (!objDetailsDAO.dob.equals(""))
       {
        LocalDate date = LocalDate.parse(objDetailsDAO.dob);
        
        dob.setValue(date);
       }
        lblId.setText(objDetailsDAO.usertype);
        
    }    
    
    /**
     * This method will save the details of respective user in the database.
     * if the fields are empty a popup is displayed.
     * @param event 
     */
    @FXML
    private void onsave(ActionEvent event) 
            
    {
        if(validateFields())
        {
        DetailsModel objDetailsModel =new DetailsModel();
        objDetailsModel.setGen((String) gender.getSelectionModel().getSelectedItem());
        objDetailsModel.setAdd(address.getText());
        objDetailsModel.setSt(contact.getText());
        
        DetailsDAO objDetailsDAO = new DetailsDAO();
        objDetailsDAO.save(
                dob.getValue().toString(), 
                objDetailsModel.getGen(), 
                objDetailsModel.getAdd(),
                objDetailsModel.getSt());
        }
    }
        
    /**
     * Clear fields
     */    
    public void clearFields()
    {
        fullname.clear();
        address.clear();
        contact.clear(); 
    }
    
    /**
     * On back button it goes back to main screen.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onback(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/fp.fxml"));
        pane2.getChildren().setAll(pane);
        
    }
    
    /**
     * To check if the field are blank.
     * @return 
     */
    private boolean validateFields()
    {
        if(address.getText().equals("") | contact.getText().equals("") )
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All Fields");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
}
