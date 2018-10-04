//Declaring packages
package Controller;

//import statements
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * Admin Controller Class 
 * The initializer controller class is present.
 */
public class AdminController implements Initializable {

    @FXML
    private ScrollPane sp;
    @FXML
    private AnchorPane pane2;
    @FXML
    private ComboBox databasetable;
    @FXML
    private Button show;
    
    ObservableList<String> maintables = FXCollections.observableArrayList("Patient Details","Doctor Details","Lab Technician Details");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        databasetable.setItems(maintables);
    }    
    /**
     * on show displays the respective table views for admin.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onshow(ActionEvent event) throws IOException 
    {
        String aaa = (String)databasetable.getSelectionModel().getSelectedItem();
        
        if (aaa.equals("Patient Details"))
                    {                        
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/AdminPateint.fxml"));
                    pane2.getChildren().setAll(pane);
                    }
        
        if (aaa.equals("Doctor Details"))
                    {                        
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/AdminDoctor.fxml"));
                    pane2.getChildren().setAll(pane);
                    }
        
        if (aaa.equals("Lab Technician Details"))
                    {                        
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/AdminLabTechnician.fxml"));
                    pane2.getChildren().setAll(pane);
                    }
        
                
    }
    
}
