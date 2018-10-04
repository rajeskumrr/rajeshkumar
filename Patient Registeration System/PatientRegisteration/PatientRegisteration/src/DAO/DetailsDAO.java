//Package
package DAO;

//import statements
import Controller.DetailsController;
import Controller.RegistrationController;
import Model.SignInModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JOptionPane;

import jdk.nashorn.internal.ir.CatchNode;
import org.controlsfx.control.Notifications;

/**
 * By the help of the select query data is populated by default when different pages open up.
 * @author SEAN GEORGE
 */
public class DetailsDAO {
    public static String name = "";
    public static String dob = "";
    public static String usertype = "";
    public static String userType="";
    public static String userEmail="";
    public static String details_gender, details_address, details_contact;
    
    public void pageLoad(){ 
            
        
        Connection conn=null;
        PreparedStatement pstmt = null;
               
        try{
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");

       SignInModel objSignInModel = new SignInModel();
       userType = objSignInModel.getUserType();
       userEmail = objSignInModel.getUserEmail();
                
       if (userType.equals("1"))//admin
       {
              pstmt=conn.prepareStatement("SELECT admin_Details_FN , admin_Details_LN , admin_Details_DOB, "
                      + "admin_Details_gender, admin_Details_address, admin_Details_contact FROM fp.admin_details WHERE "
                    + "admin_details_email = ? "
                    + "AND user_type_id= ? ;" );
              pstmt.setString(1,userEmail);
              pstmt.setString(2,userType);
                 ResultSet rs = pstmt.executeQuery();
                               
                while (rs.next())
                {
                    name = rs.getString(1);
                    name = name + " "+rs.getString(2);
                    dob = rs.getString(3);
                    details_gender = rs.getString(4);
                    details_address = rs.getString(5);
                    details_contact = rs.getString(6);
                }
                usertype = "Admin Details";
       }
       if (userType.equals("2"))//Patient
       {
           pstmt=conn.prepareStatement("SELECT Patient_Details_FN , Patient_Details_LN , Patient_Details_DOB , "
                   + "Patient_Details_gender, Patient_Details_address, Patient_Details_contact FROM fp.patient_details WHERE "
                    + "patient_details_email = ? "
                    + "AND user_type_id= ? ;" );
                         pstmt.setString(1,userEmail);
              pstmt.setString(2,userType);
                 ResultSet rs = pstmt.executeQuery();
                               
                while (rs.next())
                {
                    name = rs.getString(1);
                    name = name + " "+rs.getString(2);
                    dob = rs.getString(3);
                                        details_gender = rs.getString(4);
                    details_address = rs.getString(5);
                    details_contact = rs.getString(6);
                }
                usertype = "Patient Details";
       }
       if (userType.equals("3"))//Doctor
       {
                         pstmt=conn.prepareStatement("SELECT doctor_Details_FN , doctor_Details_LN , doctor_Details_DOB, "
                                 + "doctor_Details_gender, doctor_Details_address , doctor_Details_contact  FROM fp.doctor_details WHERE "
                    + "doctor_details_email = ? "
                    + "AND user_type_id= ? ;" );
                                       pstmt.setString(1,userEmail);
              pstmt.setString(2,userType);
                 ResultSet rs = pstmt.executeQuery();
                               
                while (rs.next())
                {
                    name = rs.getString(1);
                    name = name + " "+rs.getString(2);
                    dob = rs.getString(3);
                    details_gender = rs.getString(4);
                    details_address = rs.getString(5);
                    details_contact = rs.getString(6);
                }
                usertype = "Doctor Details";

       }
       if (userType.equals("4"))//lab
       {
                         pstmt=conn.prepareStatement("SELECT lab_technician_Details_FN , lab_technician_Details_LN,  "
                                 + "lab_technician_Details_address,lab_technician_Details_contact  FROM fp.lab_technician_details WHERE "
                    + "lab_technician_details_email = ? "
                    + "AND user_type_id= ? ;" );
                         pstmt.setString(1,userEmail);
                         pstmt.setString(2,userType);
                         
                         
                 ResultSet rs = pstmt.executeQuery();
                               
                while (rs.next())
                {
                    name = rs.getString(1);
                    name = name + " "+rs.getString(2);
                    
                                        
                    details_address = rs.getString(3);
                    details_contact = rs.getString(4);
                }
                usertype = "Lab Techncian Details";

       }
       
               
               
        }
        catch(Exception e)
                {
                System.out.print(e);
                }
        
     
    }
    
    
    
     public void save(String details_dob, String details_gender, String details_address, String details_contact){ 
      Connection conn=null;
        PreparedStatement pstmt = null;
         try{
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
            
                
                pstmt=conn.prepareStatement("UPDATE `fp`.`doctor_details` SET "
                  + "`doctor_Details_DOB`=?, "
                  + "`doctor_Details_Gender`=?, "
                  + "`doctor_Details_Address`=?, "
                  + "`doctor_Details_Contact`=? "
                  + "WHERE "
                  + "`User_Type_Id`= ? and "
                  + "`doctor_details_email` = ?;");
                
                pstmt.setString(1,details_dob);
                pstmt.setString(2,details_gender);
                pstmt.setString(3,details_address);
                pstmt.setString(4,details_contact);
                pstmt.setString(5,userType);
                pstmt.setString(6,userEmail);

                pstmt.executeUpdate();
       
          pstmt=conn.prepareStatement("UPDATE `fp`.`patient_details` SET "
                  + "`Patient_Details_DOB`=?, "
                  + "`Patient_Details_Gender`=?, "
                  + "`Patient_Details_Address`=?, "
                  + "`Patient_Details_Contact`=? "
                  + "WHERE "
                  + "`User_Type_Id`=? and "
                  + "`patient_details_email` = ?;" );
                
                pstmt.setString(1,details_dob);
                pstmt.setString(2,details_gender);
                pstmt.setString(3,details_address);
                pstmt.setString(4,details_contact);
                pstmt.setString(5,userType);
                pstmt.setString(6,userEmail);

                pstmt.executeUpdate();
                
                pstmt=conn.prepareStatement("UPDATE `fp`.`admin_details` SET "
                  + "`admin_Details_DOB`=?, "
                  + "`admin_Details_Gender`=?, "
                  + "`admin_Details_Address`=?, "
                  + "`admin_Details_Contact`=? "
                  + "WHERE "
                  + "`User_Type_Id`=? and "
                  + "`admin_details_email` = ?;" );
                
                pstmt.setString(1,details_dob);
                pstmt.setString(2,details_gender);
                pstmt.setString(3,details_address);
                pstmt.setString(4,details_contact);
                pstmt.setString(5,userType);
                pstmt.setString(6,userEmail);

                pstmt.executeUpdate();
                
                pstmt=conn.prepareStatement("UPDATE `fp`.`lab_technician_details` SET "
                  + "`lab_technician_Details_Address`=?, "
                  + "`lab_technician_Details_Contact`=? "
                  + "WHERE "
                  + "`User_Type_Id`=? and "
                  + "`lab_technician_details_email` = ?;" );
                
                pstmt.setString(1,details_address);
                pstmt.setString(2,details_contact);
                pstmt.setString(3,userType);
                pstmt.setString(4,userEmail);

                pstmt.executeUpdate();
                
                
        
                    Image img = new Image("/image/small_tick2.png") ;   
                    Notifications notificationBuilder = Notifications.create()
                                  .title("Information")
                                  .text("User Registered")
                                  .graphic(new ImageView(img))
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();    
         }
          catch(Exception e)
            {
                System.out.println(e);
            }
     }
    
}
        
        
       
        
        
