/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBConnection;
import DAO.LabRecordsDAO;
import Model.SignInModel;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author SEAN GEORGE
 */
public class LabRecordsController implements Initializable{

    @FXML
    private ComboBox patientemail1;
    @FXML
    private TextArea remarks1;
    @FXML
    private Button send;
    @FXML
    private TableView<?> tablemessage;
    @FXML
    private TableColumn<?, ?> labptnm;
    @FXML
    private TableColumn<?, ?> labdocnm;
    @FXML
    private TableColumn<?, ?> res;
    @FXML
    private TableColumn<?, ?> rem;
    @FXML
    private Label error_textarea;
    @FXML
    private ComboBox result1;
    
    ObservableList<String> resultList = FXCollections.observableArrayList("Positive","Negative");
     private ObservableList<String>patmail;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       patmail =FXCollections.observableArrayList(); 
        
        result1.setItems(resultList);
        ResultSet res;
        LabRecordsDAO objLabRecordsDAO = new LabRecordsDAO();
         res = objLabRecordsDAO.senderload();        
       try {
            while (res.next())
            {
                
                patmail.add(res.getString(1));
                
            }
                         patientemail1.setItems(patmail);
        patientemail1.getSelectionModel().selectFirst();
        
      } catch (Exception ex) { 
      } 
        
        
    }
    
    
    @FXML
    private void send(ActionEvent event) {
          PreparedStatement pstmt = null;
        //Connection DBConnection.pmartConnection()=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
          //      Class.forName("com.mysql.jdbc.Driver");
            //    DBConnection.pmartConnection()=DriverManager.getConnection("jdbc:mysql:///patient?characterEncoding=UTF-8&useSSL=false","root","12345678");
                
                
                pstmt=DBConnection.pmartConnection().prepareStatement("INSERT INTO `fp`.`lab_records` (`Patient_Details_Id`, `Lab_Records_Result`, `Lab_Records_Remarks`) \n" +
"VALUES( (select patient_details.patient_details_id from fp.patient_details where patient_details.patient_details_email=?), \n" +
"?, ?);");                
                pstmt.setString(1,patientemail1.getSelectionModel().getSelectedItem().toString());
                pstmt.setString(2,result1.getSelectionModel().getSelectedItem().toString());
                pstmt.setString(3,remarks1.getText());
                
                
                   
                pstmt.executeUpdate();
             
                Image img = new Image("/image/small_tick2.png") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Information")
                                  .text("Report Sent!!")
                                  .graphic(new ImageView(img))
                                  
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();
    }
        catch(Exception e){}
    }
    

    
}
