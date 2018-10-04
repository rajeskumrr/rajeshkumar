//Package
package Controller;

//Import
import Model.AdminLabTechnicianModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 * Public class AdminLabTechnicianController contains the textfields, buttons and Table columns.
 */
public class AdminLabTechnicianController implements Initializable{

    @FXML
    private AnchorPane pane5;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private ScrollPane sp;
    @FXML
    private TableView<AdminLabTechnicianModel> tablelabtech;
    @FXML
    private TableColumn<AdminLabTechnicianModel,String> labtechid;
    @FXML
    private TableColumn<AdminLabTechnicianModel,String> labtechfnm;
    @FXML
    private TableColumn<AdminLabTechnicianModel,String> labtechlnm;
    @FXML
    private TableColumn<AdminLabTechnicianModel,String> labtechadd;
    @FXML
    private TableColumn<AdminLabTechnicianModel,String> labtechcon;
    @FXML
    private TableColumn<AdminLabTechnicianModel,String> labtechemail;
    @FXML
    private TextField labtechid1;
    @FXML
    private TextField labtechfnm1;
    @FXML
    private TextField labtechlnm1;
    @FXML
    private TextField labtechcon1;
    @FXML
    private TextField labtechadd1;
    @FXML
    private TextField labtechemail1;
    
    private ObservableList<AdminLabTechnicianModel>data;
    
    private Connection con=null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    /**
	 * Initializes the controller class.
	 */
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        try {
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setCellTable();
        loadDataFromDatabase();
        setCellValueFromTableToTextField();
    }
    
    /**
     * This method Sets the value of the Table Columns
     */
    private void setCellTable()
    {
         labtechid.setCellValueFactory(new PropertyValueFactory<>("labtechdetid"));
         labtechfnm.setCellValueFactory(new PropertyValueFactory<>("labtechdetfnm"));
         labtechlnm.setCellValueFactory(new PropertyValueFactory<>("labtechdetlnm"));
         labtechadd.setCellValueFactory(new PropertyValueFactory<>("labtechdetadd"));
         labtechcon.setCellValueFactory(new PropertyValueFactory<>("labtechdetcont"));
         labtechemail.setCellValueFactory(new PropertyValueFactory<>("labtechdetemail"));
    }
    
    /**
     * This methods loads the data from the lab technician details database and populates it in the jtable.
     */
    private void loadDataFromDatabase()
    {
        try {
            data = FXCollections.observableArrayList();
            pst = con.prepareStatement("Select Lab_Technician_Details_Id,Lab_Technician_Details_FN,Lab_Technician_Details_LN,Lab_Technician_Details_Address,Lab_Technician_Details_Contact,lab_technician_details_email from fp.lab_technician_details;");
            rs = pst.executeQuery();
            while(rs.next()){
                
               
                data.add(new AdminLabTechnicianModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablelabtech.setItems(data);
    }
    
    /**
     * This method sets the value from the table to the respective textfields.
     */
    private void setCellValueFromTableToTextField()
    {
        tablelabtech.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                AdminLabTechnicianModel lt = tablelabtech.getItems().get(tablelabtech.getSelectionModel().getSelectedIndex());
                labtechid1.setText(lt.getLabtechdetid());
                labtechfnm1.setText(lt.getLabtechdetfnm());
                labtechlnm1.setText(lt.getLabtechdetlnm());
                labtechadd1.setText(lt.getLabtechdetadd());
                labtechcon1.setText(lt.getLabtechdetcont());
                labtechemail1.setText(lt.getLabtechdetemail());        
                        
            }
        });
      
    }
   
    /**
     * Updates the lab technician details table when the update button is clicked.
     * When the button is clicked, the method validateFields is checked.
     * @param event 
     */
    @FXML
    private void OnUpdate(ActionEvent event) 
    {
        if (validateFields())
        {
        String sql = "Update fp.lab_technician_details set Lab_Technician_Details_FN = ?,Lab_Technician_Details_LN = ?,Lab_Technician_Details_Address = ?,Lab_Technician_Details_Contact = ?,lab_technician_details_email = ? where Lab_Technician_Details_Id = ? ";
        try {
            String labtechid = labtechid1.getText();
            String firstname = labtechfnm1.getText();
            String lastname = labtechlnm1.getText();
            String add = labtechadd1.getText();
            String cont = labtechcon1.getText();
            String email = labtechemail1.getText();
            pst = con.prepareStatement(sql);
            pst.setString(1,firstname);
            pst.setString(2,lastname);
            pst.setString(3,add);
            pst.setString(4,cont);
            pst.setString(5,email);
            pst.setString(6,labtechid);
            
            int i = pst.executeUpdate();
            if( i == 1){
                Image img = new Image("/image/small_tick2.png") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Information")
                                  .text("Updated Successfully")
                                  .graphic(new ImageView(img))
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();
                                  loadDataFromDatabase();
                                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminLabTechnicianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
    }

    /**
     * Deletes a record from the Lab Technician details table/j table.
     * @param event 
     */ 
    @FXML
    private void Ondelete(ActionEvent event) 
    {
        String sql = "Delete from fp.lab_technician_details where Lab_Technician_Details_Id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,labtechid1.getText());
            int i = pst.executeUpdate();
            
            if(i == 1){
                Image img = new Image("/image/Delete.jpg") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .text("Information") 
                                  .title("Record Deleted Successfully")
                                  .graphic(new ImageView(img))
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();
                                  loadDataFromDatabase();
                                  clearFields();
                               
                                  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminLabTechnicianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Clears the Fields
     */
    private void clearFields()
    {
     
        labtechid1.clear();
        labtechfnm1.clear();
        labtechlnm1.clear();
        labtechadd1.clear();
        labtechcon1.clear();
        labtechemail1.clear();
         
    }
    
    /**
     * This method will validate the fields if empty.
     * If the fields are empty an alert box pops up.
     * @return 
     */
    private boolean validateFields()
    {
        if(labtechid1.getText().equals("") 
            | labtechfnm1.getText().equals("") 
                | labtechlnm1.getText().equals("")
                | labtechadd1.getText().equals("")
                | labtechcon1.getText().equals("")
                | labtechemail1.getText().equals("") )
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
