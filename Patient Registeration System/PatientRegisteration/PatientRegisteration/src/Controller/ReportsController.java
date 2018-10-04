//Package
package Controller;

//Import statements
import DAO.ReportsDAO;
import Model.ReportsModel;
import Model.SignInModel;
import Model.DetailsModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *The patients lab details are visible to him/her in a j table format.
 */

public class ReportsController implements Initializable {
    
    @FXML
    private TableView<ReportsModel> tablepatient;
    
     @FXML 
     private TableColumn <ReportsModel,String>PatientName;
          
            @FXML
            private TableColumn <ReportsModel,String>Result;
            @FXML
            private TableColumn <ReportsModel,String>Remark;
            
    
    private ObservableList<ReportsModel>data;
    @FXML
    private Button back;
    @FXML
    private AnchorPane pane1;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      load();

        
    }  
    
    
    public void load(){
      ReportsDAO objReportsDAO = new ReportsDAO(); 
        data=FXCollections.observableArrayList();
        ResultSet rs =objReportsDAO.loaddetails();
      try {
        
        
            while (rs.next())
            {
                data.add(new ReportsModel(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        
      } catch (Exception ex) {
            
        }
      
        PatientName.setCellValueFactory(new PropertyValueFactory<>("pname"));
        Result.setCellValueFactory(new PropertyValueFactory<>("result"));
        Remark.setCellValueFactory(new PropertyValueFactory<>("remark"));
        tablepatient.setItems(data);
}

   
}
