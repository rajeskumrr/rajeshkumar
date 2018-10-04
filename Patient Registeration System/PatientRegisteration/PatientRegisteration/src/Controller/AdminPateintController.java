//package
package Controller;

//Import
import Model.AdminPatient;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
 *FXML Controller class
 *Public class AdminLabTechnicianController contains the textfields, buttons and Table columns.
 */
public class AdminPateintController implements Initializable {

	@FXML
	private AnchorPane pane3;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TableView<AdminPatient> tablepatient;
        @FXML
	private TableColumn<AdminPatient, String> patientif;
        @FXML
	private TableColumn<AdminPatient, String> fnm;
        @FXML
	private TableColumn<AdminPatient, String> lnm;
        @FXML
	private TableColumn<AdminPatient, String> gen;
        @FXML
	private TableColumn<AdminPatient, String> add;
        @FXML
	private TableColumn<AdminPatient, String> dob;
        @FXML
        private TableColumn<AdminPatient, String> con;
        @FXML
        private TableColumn<AdminPatient, String> usrid;
        @FXML
	private TableColumn<AdminPatient, String> email;
	
        
	

	private ObservableList<AdminPatient> data1;
        @FXML
	private TextField patientid;
        @FXML
	private TextField firstname;
        @FXML
	private TextField lastname;
	@FXML
	private TextField add1;
	@FXML
	private TextField status1;
        @FXML
	private TextField dob1;
        @FXML
	private TextField gen1;
	@FXML
	private TextField email1;
	@FXML
	private ScrollPane sp1;
    @FXML
    private TextField con1;
    @FXML
    private TextField userid;
   

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// TODO
		Onload();
		setCellValueFromTableToTextField();
		sp1.setHmax(3);
		sp1.setVmax(3);

	}
        
        /**
         * This method loads data from the patient detials table in the database. 
         */
	private void Onload()

	{

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp", "dbfp",
					"510");

			data1 = FXCollections.observableArrayList();

			PreparedStatement stmt = null;

			

			stmt = conn.prepareStatement("Select * from fp.patient_details;");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				data1.add(new AdminPatient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
						
			}

		} catch (Exception e) {

		}

		patientif.setCellValueFactory(new PropertyValueFactory<>("pdid"));
		fnm.setCellValueFactory(new PropertyValueFactory<>("pdfnm"));
		lnm.setCellValueFactory(new PropertyValueFactory<>("pdlnm"));
                gen.setCellValueFactory(new PropertyValueFactory<>("pdgen"));
                add.setCellValueFactory(new PropertyValueFactory<>("pdadd"));
                con.setCellValueFactory(new PropertyValueFactory<>("pdcont"));
		dob.setCellValueFactory(new PropertyValueFactory<>("pddob"));
                usrid.setCellValueFactory(new PropertyValueFactory<>("pduserid"));
		email.setCellValueFactory(new PropertyValueFactory<>("pdemail"));
		

		tablepatient.setItems(data1);
	}
        
        /**
         * Updates the patient details table when the update button is clicked.
         * @param event
         * @throws ClassNotFoundException 
         */
	@FXML
	private void OnUpdate(ActionEvent event) throws ClassNotFoundException

	{

            
            if (validateFields())
            {
		Connection conn1 = null;
		PreparedStatement pst = null;

		String sql = "Update fp.patient_details set Patient_Details_FN = ?,Patient_Details_LN =?,Patient_Details_Gender =?,Patient_Details_Address =?,Patient_Details_Contact =?,Patient_Details_DOB = ?,User_Type_Id = ?,patient_details_email =? WHERE PATIENT_DETAILS_ID = ?";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn1 = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp", "dbfp",
					"510");

			String pdid = patientid.getText();
			String pdfnm = firstname.getText();
			String pdlnm = lastname.getText();
                        String pdgen = gen1.getText();
                        String pdadd = add1.getText();
                        String pdcon = con1.getText();
			String pddob = dob1.getText();
                        String pduserid = userid.getText();
			String pdemail = email1.getText();
		
			pst = conn1.prepareStatement(sql);
			pst.setString(1, pdfnm);
			pst.setString(2, pdlnm);
                        pst.setString(3, pdgen);
                        pst.setString(4, pdadd);
                        pst.setString(5, pdcon);
			pst.setString(6, pddob);
			pst.setString(7, pduserid);
			pst.setString(8, pdemail);
			pst.setString(9, pdid);

			int i = pst.executeUpdate();
			if (i == 1) {
				Image img = new Image("/image/small_tick2.png");
				Notifications notificationBuilder = Notifications.create()
                                                .title("Information")
                                                .text("Updated Successfully")
						.graphic(new ImageView(img))
						.hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
                                                notificationBuilder.darkStyle();
				                notificationBuilder.show();
				Onload();
			}

		} catch (SQLException ex) {
			Logger.getLogger(AdminPateintController.class.getName()).log(Level.SEVERE, null, ex);
                }	}
            
            
	}
            
        /**
         * Deletes a record from the Patient details table/j table.
         * @param event 
         */
	@FXML
	private void Ondelete(ActionEvent event) {
		Connection conn2 = null;
		PreparedStatement pst1 = null;
                

		String sql1 = "Delete from fp.patient_details where PATIENT_DETAILS_ID = ?;";
                String sql2 = "Delete from fp.appointment_details where PATIENT_DETAILS_ID = ?;";
                String sql3 = "Delete from fp.lab_records where PATIENT_DETAILS_ID = ?;";
                int i;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn2 = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp", "dbfp",
					"510");

			pst1 = conn2.prepareStatement(sql2);
			pst1.setString(1,patientid.getText());
			pst1.executeUpdate();
                        
                        pst1 = conn2.prepareStatement(sql3);
			pst1.setString(1,patientid.getText());
			pst1.executeUpdate();
                                                
                        pst1 = conn2.prepareStatement(sql1);
			pst1.setString(1,patientid.getText());
			i = pst1.executeUpdate();

			if (i == 1) {
				Image img = new Image("/image/Delete.jpg");
				Notifications notificationBuilder = Notifications.create()
                                        .text("Information")
                                        .title("Deleted Successfully")
						.graphic(new ImageView(img))
						// .graphic(null)
						.hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
                                                notificationBuilder.darkStyle();
				notificationBuilder.show();
				Onload();
				clearFields();
			}

		} catch (SQLException ex) {
			Logger.getLogger(AdminPateintController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(AdminPateintController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
        
        /**
         * This method sets the value from the table to the respective textfields.
         */
	private void setCellValueFromTableToTextField() {
		tablepatient.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AdminPatient ap = tablepatient.getItems().get(tablepatient.getSelectionModel().getSelectedIndex());
				patientid.setText(ap.getPdid());
				firstname.setText(ap.getPdfnm());
				lastname.setText(ap.getPdlnm());
                                gen1.setText(ap.getPdgen());
                                add1.setText(ap.getPdadd());
                                con1.setText(ap.getPdcont());
				dob1.setText(ap.getPddob());
				userid.setText(ap.getPduserid());				
				email1.setText(ap.getPdemail());
				

			}
		});

               
	}
        /**
         * Will clear textfields.
         */
	private void clearFields() {

		patientid.clear();
		firstname.clear();
		lastname.clear();
                gen1.clear();
                add1.clear();
                con1.clear();
		dob1.clear();
                userid.clear();
		email1.clear();
	}
    
     /**
      * This method will validate the fields if empty.
        * If the fields are empty an alert box pops up.
      * @return 
      */   
    private boolean validateFields()
    {
        if(patientid.getText().equals("") | firstname.getText().equals("") 
                | lastname.getText().equals("")| gen1.getText().equals("")| add1.getText().equals("")| con1.getText().equals("")| dob1.getText().equals("")
                | userid.getText().equals("")| email.getText().equals("") )
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
