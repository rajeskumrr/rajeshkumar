//package
package Controller;

//Import  statements
import DAO.MessagesDAO;
import Model.MessagesModel;
import Model.ReportsModel;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *This class contains the buttons,textfields and actions.
 * Here the patient and the doctor can send messages to each other.
 */
public class MessagesController implements Initializable {

    @FXML
    private TableView<MessagesModel> tablemessage;
    @FXML
    private TableColumn<MessagesModel, String> date;
    @FXML
    private TableColumn<MessagesModel, String> sender;
    @FXML
    private TableColumn<MessagesModel, String> receiver;
    @FXML
    private TableColumn<MessagesModel, String> messages;
    @FXML
    private ComboBox receiverName;
    @FXML
    private TextArea message;
    @FXML
    private Button send;

    private ObservableList<MessagesModel>data;
    private ObservableList<String>receivermail;
    public String[] arr = new String[200];
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
        MessagesDAO objMessagesDAO = new MessagesDAO();
        data=FXCollections.observableArrayList();
        ResultSet rs =objMessagesDAO.pageload();
      try {
        
        
            while (rs.next())
            {
                data.add(new MessagesModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        
      } catch (Exception ex) {
            
        
      }
        tablemessage.setItems(data);
        
        date.setCellValueFactory(new PropertyValueFactory("date"));
        sender.setCellValueFactory(new PropertyValueFactory("sender"));
        receiver.setCellValueFactory(new PropertyValueFactory("receiver"));
        messages.setCellValueFactory(new PropertyValueFactory("messages"));
        
         
        
        ResultSet res =objMessagesDAO.pageload();
        receivermail =FXCollections.observableArrayList();
        
        res = objMessagesDAO.senderload();        
       try {
            while (res.next())
            {
                
                receivermail.add(res.getString(1));
                
            }
                         receiverName.setItems(receivermail);
        receiverName.getSelectionModel().selectFirst();
        
      } catch (Exception ex) { 
      } 
        
   
    }    
    
    /**
     * On send button this will send the message to the doctor, after checking if the fields are empty or not.
     * @param event 
     */
    @FXML
    private void send(ActionEvent event) 
    {
   
        if (validateFields())
        {   
        String msg = message.getText();
        String recMail = receiverName.getSelectionModel().getSelectedItem().toString();
        String dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        MessagesDAO objMessagesDAO = new MessagesDAO();       
        objMessagesDAO.send(msg, recMail, dates);       
         Image img = new Image("/image/small_tick2.png") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Information")
                                  .text("Message Sent!!")
                                  .graphic(new ImageView(img))
                                  
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();
        }
        
    }
    
    /**
     * This is the method to validate if the fields are empty.
     * @return 
     */
    private boolean validateFields()
    {
        if(message.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Message In Text Area");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    public void clearFields()
    {       
        message.clear();
        
    }
    
}