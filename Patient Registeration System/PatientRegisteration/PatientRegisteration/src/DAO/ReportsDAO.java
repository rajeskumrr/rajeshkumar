//Package
package DAO;

//Import
import Model.ReportsModel;
import Model.SignInModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * All the detials/reports are visible to patient using mutiple queries.
 * @author SEAN GEORGE
 */
public class ReportsDAO {
    
     public ResultSet rs ;
    public ResultSet loaddetails() 
    
    {
        
        PreparedStatement pstmt = null;
        Connection conn=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
                
                
                pstmt=conn.prepareStatement("SELECT CONCAT(patient_details.Patient_Details_FN,\" \",patient_details.Patient_Details_LN) as PatientName, \n" +
"lab_records.Lab_Records_Result, lab_records.Lab_Records_Remarks\n" +
"from `fp`.`lab_records` \n" +
"inner join `fp`.`patient_details` on patient_details.Patient_Details_Id = lab_records.Patient_Details_Id\n" +
"where patient_details.Patient_Details_Id = \n" +
"(select patient_details.patient_details_id from fp.patient_details where patient_details.patient_details_email=?);");
                pstmt.setString(1,(objSignInModel.getUserEmail()));
                
                
                  rs = pstmt.executeQuery();
             
    }
        catch(Exception e)
        {
    
        }
       
        return rs;
        
        
        
    }
}
