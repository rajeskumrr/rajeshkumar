//Package
package Controller;

//Importt statements
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 */
public class HistoryController implements Initializable {

    @FXML
    private TableView<?> tablepatient;
    @FXML
    private TableColumn<?, ?> PatientName;
    @FXML
    private TableColumn<?, ?> DocName;
    @FXML
    private TableColumn<?, ?> Result;
    @FXML
    private TableColumn<?, ?> Remark;
    @FXML
    private TableColumn<?, ?> HospName;
    @FXML
    private TableColumn<?, ?> LabAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
