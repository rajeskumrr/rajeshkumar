//Package
package DAO;

//Import statements
import Model.SignInModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *In this class, the appointment data is displayed based on usertype.
 */
public class AppointmentsDAO {
     public static ResultSet rs ;

    public ResultSet pageload()        
    {
    
         PreparedStatement pstmt = null;
        Connection conn=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
                
                if (objSignInModel.getUserType().equals("2"))//patient
                {
                pstmt=conn.prepareStatement("select \n" +
"CONCAT(doctor_details.Doctor_Details_FN, \" \", doctor_details.Doctor_Details_LN) as Doctor_Name,\n" +
"CONCAT(patient_details.Patient_Details_FN,\" \",patient_details.Patient_Details_LN) as Patient_Name,\n" +
"Appointment_Details_Date, Appointment_Details_Status, Appointment_Details_Time, Appointment_Details_id \n" +
"from fp.appointment_details \n" +
"inner join fp.patient_details on patient_details.Patient_Details_Id = appointment_details.Patient_Details_Id\n" +
"inner join fp.doctor_details on doctor_details.doctor_Details_Id = appointment_details.doctor_Details_Id\n" +
"where patient_details.patient_details_email = ?\n" +
"order by Appointment_Details_Date desc;");             
                pstmt.setString(1,(objSignInModel.getUserEmail()));
     }
                else if(objSignInModel.getUserType().equals("3"))//doctor
                {
                         pstmt=conn.prepareStatement("select \n" +
"CONCAT(doctor_details.Doctor_Details_FN, \" \", doctor_details.Doctor_Details_LN) as Doctor_Name,\n" +
"CONCAT(patient_details.Patient_Details_FN,\" \",patient_details.Patient_Details_LN) as Patient_Name,\n" +
"Appointment_Details_Date, Appointment_Details_Status, Appointment_Details_Time, Appointment_Details_id \n" +
"from fp.appointment_details \n" +
"inner join fp.patient_details on patient_details.Patient_Details_Id = appointment_details.Patient_Details_Id\n" +
"inner join fp.doctor_details on doctor_details.doctor_Details_Id = appointment_details.doctor_Details_Id\n" +
"where doctor_details.doctor_Details_email = ?\n" +
"order by Appointment_Details_Date desc;");
                         pstmt.setString(1,(objSignInModel.getUserEmail()));
     }
                  rs = pstmt.executeQuery();
             
    }
        catch(Exception e)
        {
    
        }
       
        return rs;
        
    }
    
    
     
     public ResultSet docMail()        
    {
    
         PreparedStatement pstmt = null;
        Connection conn=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
                
                if (objSignInModel.getUserType().equals("2")){
                pstmt=conn.prepareStatement("select doctor_details.doctor_details_email from  fp.doctor_details;");                
                }
                
                rs = pstmt.executeQuery();
             
    }
        catch(Exception e)
        {
    
        }
       
        return rs;
        
    }
     
     
     
     
     
       public void bookDAO(String dates, String docmail, String slot)        
    {
    
         PreparedStatement pstmt = null;
        Connection conn=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
                
                
                pstmt=conn.prepareStatement("INSERT INTO `fp`.`appointment_details` \n" +
"(`Doctor_Details_Id`, `Patient_Details_Id`, `Appointment_Details_Date`, `Appointment_Details_Status`, `Appointment_Details_Time`) \n" +
"VALUES ((select doctor_details.doctor_details_id from fp.doctor_details where doctor_details.doctor_details_email=?),\n" +
" (select patient_details.patient_details_id from fp.patient_details where patient_details.patient_details_email=?) , \n" +
" ?, 'Booked', ?);");                
                pstmt.setString(1,docmail);
                pstmt.setString(2,(objSignInModel.getUserEmail()));
                pstmt.setString(3,dates);
                pstmt.setString(4,slot);
                
                pstmt.executeUpdate();
             
    }
        catch(Exception e)
        {
    
        }
       
        
        
        
    }
     
     
    
}
