//Package
package Controller;

//Import statements
import Model.AdminDoctorModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 * Public class AdminDoctor contains the textfields, buttons and Table columns.
 */
public class AdminDoctor implements Initializable
{

    @FXML
    private AnchorPane pane4;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableColumn<AdminDoctorModel, String> doctorid;
    @FXML
    private TableColumn<AdminDoctorModel, String> firstname;
    @FXML
    private TableColumn<AdminDoctorModel, String> lastname;
    @FXML
    private TableColumn<AdminDoctorModel, String> speciality;
    @FXML
    private TableColumn<AdminDoctorModel, String> registrationid;
    @FXML
    private TableColumn<AdminDoctorModel, String> doctoremail;
    
    private ObservableList<AdminDoctorModel>data;
    
    @FXML
    private TextField doctorid1;
    @FXML
    private TextField firstname1;
    @FXML
    private TextField lastname1;
    @FXML
    private TextField registrationid1;
    @FXML
    private TextField speciality1;
    @FXML
    private TextField email1;
    @FXML
    private TableView<AdminDoctorModel> tabledoctor;
    
    private Connection con=null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @FXML
    private ScrollPane sp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
         doctorid.setCellValueFactory(new PropertyValueFactory<>("did"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("dfnm"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("dlnm"));
         speciality.setCellValueFactory(new PropertyValueFactory<>("dspeciality"));
         registrationid.setCellValueFactory(new PropertyValueFactory<>("dregid"));
         doctoremail.setCellValueFactory(new PropertyValueFactory<>("demail"));
    }
    /**
     * This methods loads the data from the doctor details database and populates it in the jtable.
     */
    private void loadDataFromDatabase()
    {
        try {
            data = FXCollections.observableArrayList();
            pst = con.prepareStatement("Select Doctor_Details_id,Doctor_Details_FN,Doctor_Details_LN,Doctor_Details_Speciality,Doctor_Details_Reg_id,doctor_details_email from fp.doctor_details;");
            rs = pst.executeQuery();
            while(rs.next()){
                
                data.add(new AdminDoctorModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabledoctor.setItems(data);
    }
    
    /**
     * This method sets the value from the table to the respective textfields.
     */
    private void setCellValueFromTableToTextField()
    {
        tabledoctor.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                AdminDoctorModel ad = tabledoctor.getItems().get(tabledoctor.getSelectionModel().getSelectedIndex());
                doctorid1.setText(ad.getDid());
                firstname1.setText(ad.getDfnm());
                lastname1.setText(ad.getDlnm());
                speciality1.setText(ad.getDspeciality());
                registrationid1.setText(ad.getDregid());
                email1.setText(ad.getDemail());
                        
                        
            }
        });
      
    }

    /**
     * Updates the doctor details table when the update button is clicked.
     * When the button is clicked, the method validateFields is checked.
     * @param event 
     */
    @FXML
    private void OnUpdate(ActionEvent event) 
    {
        if ( validateFirstName() && validateLastName()&& validateSpe() && validateEmail() && validateReg() && validateFields())
        {
        String sql = "Update fp.doctor_details set Doctor_Details_FN = ?,Doctor_Details_LN = ?,Doctor_Details_Speciality = ?,Doctor_Details_Reg_id = ?,doctor_details_email = ? where Doctor_Details_Id = ? ";
        try {
            String doctorid = doctorid1.getText();
            String firstname = firstname1.getText();
            String lastname = lastname1.getText();
            String speciality = speciality1.getText();
            String registration = registrationid1.getText();
            String email = email1.getText();
            pst = con.prepareStatement(sql);
            pst.setString(1,firstname);
            pst.setString(2,lastname);
            pst.setString(3,speciality);
            pst.setString(4,registration);
            pst.setString(5,email);
            pst.setString(6,doctorid);
            
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
            Logger.getLogger(AdminDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }
    /**
     * Deletes a record from the Doctor details table/j table.
     * @param event 
     */
    @FXML
    private void Ondelete(ActionEvent event) 
    {
        String sql = "Delete from fp.doctor_details where Doctor_Details_Id = ?";
        String sql2 = "Delete from fp.appointment_details where Doctor_DETAILS_ID = ?;";
        String sql3 = "Delete from fp.lab_records where Doctor_DETAILS_ID = ?;";
        int i;
        try {
            pst = con.prepareStatement(sql2);
            pst.setString(1,doctorid1.getText());
            i = pst.executeUpdate();
            
            pst = con.prepareStatement(sql3);
            pst.setString(1,doctorid1.getText());
            i = pst.executeUpdate();
            
            pst = con.prepareStatement(sql2);
            pst.setString(1,doctorid1.getText());
            i = pst.executeUpdate();
            
            if(i == 1){
                Image img = new Image("/image/Delete.jpg") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Information")
                                  .text("Record Deleted Successfully")
                                  .graphic(new ImageView(img))
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();
                                  loadDataFromDatabase();
                                  clearFields();
                               
                                  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Clears the Fields
     */
    private void clearFields()
    {
     
        doctorid1.clear();
        firstname1.clear();
        lastname1.clear();
        speciality1.clear();
        registrationid1.clear();
        email1.clear();
         
    }
    
    /**
     * This method will validate the fields if empty.
     * If the fields are empty an alert box pops up.
     * @return 
     */
    private boolean validateFields()
    {
        if(doctorid1.getText().equals("") 
            | firstname1.getText().equals("") 
                | lastname1.getText().equals("")
                | speciality1.getText().equals("")
                | registrationid1.getText().equals("")
                | email1.getText().equals("") )
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

    private boolean validateFirstName()
    {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(firstname1.getText());
        if(m.find() && m.group().equals(firstname1.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean validateLastName()
    {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(lastname1.getText());
        if(m.find() && m.group().equals(lastname1.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Last Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Last Name");
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean validateSpe()
    {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(speciality1.getText());
        if(m.find() && m.group().equals(speciality1.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Speciality");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Speciality");
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean validateEmail()
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email1.getText());
        if(m.find() && m.group().equals(email1.getText())){
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
    
    private boolean validateReg()
    {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(registrationid1.getText());
        if(m.find() && m.group().equals(registrationid1.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Registeration Id");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Registeration Id");
            alert.showAndWait();
            return false;
        }
    }
    
    
}
