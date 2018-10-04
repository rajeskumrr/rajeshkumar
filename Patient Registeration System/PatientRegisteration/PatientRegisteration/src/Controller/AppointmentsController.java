/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentsDAO;
import DAO.MessagesDAO;
import Model.AdminPatient;
import Model.AppointmentsModel;
import Model.MessagesModel;
import Model.SignInModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Rajesh kumar
 */
public class AppointmentsController implements Initializable {

	public static String aptId;

	@FXML
	private AnchorPane pane1;
	@FXML
	private TableView<AppointmentsModel> tableappointment;

	@FXML
	private TableColumn<AppointmentsModel, String> Doctor_Name;
	@FXML
	private TableColumn<AppointmentsModel, String> Patient_Name;
	@FXML
	private TableColumn<AppointmentsModel, String> Appointment_Details_Date;
	@FXML
	private TableColumn<AppointmentsModel, String> Appointment_Details_Status;
	@FXML
	private TableColumn<AppointmentsModel, String> Appointment_Details_Time;
	@FXML
	private ComboBox doctor;
	@FXML
	private DatePicker date;
	@FXML
	private ComboBox time;
	@FXML
	private Button appointment;
	@FXML
	private Button delete;
	@FXML
	private Pane aptpane;

	/**
	 * Initializes the controller class.
	 */

	private ObservableList<AppointmentsModel> data;
	private ObservableList<String> docmail;
	ObservableList<String> timelist = FXCollections.observableArrayList("8:00", "9:00", "10:00", "11:00", "12:00",
			"13:00", "14:00", "15:00", "16:00", "17:00", "18:00");

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		tableappointment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// To change body of generated methods, choose Tools |
				// Templates.
				if (tableappointment.getSelectionModel().getSelectedItem() != null) {
					AppointmentsModel index = tableappointment.getItems()
							.get(tableappointment.getSelectionModel().getSelectedIndex());
					aptId = index.getID();
				}
			}
		});
		date.setValue(LocalDate.now());
		time.setItems(timelist);
		load();

		SignInModel objSignInModel = new SignInModel();
		if (objSignInModel.getUserType().equals("3")) {
			//
		}
	}

	public void load() {
		AppointmentsDAO objAppointmentsDAO = new AppointmentsDAO();
		data = FXCollections.observableArrayList();
		ResultSet rs = objAppointmentsDAO.pageload();
		try {

			while (rs.next()) {
				data.add(new AppointmentsModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6)));
			}

		} catch (Exception ex) {

		}
		tableappointment.setItems(data);

		Patient_Name.setCellValueFactory(new PropertyValueFactory("patname"));
		Doctor_Name.setCellValueFactory(new PropertyValueFactory("docname"));
		Appointment_Details_Date.setCellValueFactory(new PropertyValueFactory("dates"));
		Appointment_Details_Status.setCellValueFactory(new PropertyValueFactory("status"));
		Appointment_Details_Time.setCellValueFactory(new PropertyValueFactory("time"));

		ResultSet res;
		docmail = FXCollections.observableArrayList();

		res = objAppointmentsDAO.docMail();
		try {
			while (res.next()) {

				docmail.add(res.getString(1));

			}
			doctor.setItems(docmail);
			doctor.getSelectionModel().selectFirst();

		} catch (Exception ex) {
		}

	}

	@FXML
	private void book(ActionEvent event) {

		String dateStr;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dates = date.getValue();

		dateStr = (formatter.format(dates));
		String doctname = doctor.getSelectionModel().getSelectedItem().toString();
		String timeSlot = time.getSelectionModel().getSelectedItem().toString();
		AppointmentsDAO objAppointmentsDAO = new AppointmentsDAO();
		objAppointmentsDAO.bookDAO(dateStr, doctname, timeSlot);
		Image img = new Image("/image/small_tick2.png");
		Notifications notificationBuilder = Notifications.create().title("Information").text("Appointment Booked")
				.graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
		notificationBuilder.darkStyle();
		notificationBuilder.show();
		load();

	}

	@FXML
	private void Ondelete(ActionEvent event) {
		Connection conn2 = null;
		PreparedStatement pst1 = null;

		try {
			String sql1 = "Delete from fp.appointment_details where appointment_details_ID = ?";

			Class.forName("com.mysql.jdbc.Driver");
			conn2 = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");

			pst1 = conn2.prepareStatement(sql1);
			pst1.setString(1, aptId);
			int i = pst1.executeUpdate();

			if (i == 1) {
				Image img = new Image("/image/Delete.jpg");
				Notifications notificationBuilder = Notifications.create().text("Information")
						.title("Deleted Successfully").graphic(new ImageView(img))
						// .graphic(null)
						.hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
				notificationBuilder.darkStyle();
				notificationBuilder.show();
				load();

			}

		} catch (SQLException ex) {
			Logger.getLogger(AdminPateintController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(AdminPateintController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
