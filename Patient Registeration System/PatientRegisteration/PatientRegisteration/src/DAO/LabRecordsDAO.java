//Packages
package DAO;

//Import statement
import Model.SignInModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 *Messages are selected based on select query.
 */
public class LabRecordsDAO {
    
        
     public static ResultSet rs ;

   
    
    
     public ResultSet senderload()        
    {
    
         PreparedStatement pstmt = null;
        Connection conn=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
                
                
                //pstmt=conn.prepareStatement("select doctor_details.doctor_details_email from  fp.doctor_details;");                
                pstmt=conn.prepareStatement("select patient_details.patient_details_email from fp.patient_details;");
                
                
                rs = pstmt.executeQuery();
             
    }
        
        
        
        
        catch(Exception e)
        {
    
        }
       
        return rs;
        
        
    }
     
     
     
  
}
