//Package
package DAO;

//Import statement
import Controller.RegistrationController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JOptionPane;

import jdk.nashorn.internal.ir.CatchNode;
import org.controlsfx.control.Notifications;

/**
 *This is the first page for registeration.
 *Empty fields are checked.
 * Basic data is entered  and then saved.
 */
public class RegistrationDAO {
    public void dataBase(String registration_details_fn,String registration_details_ln,String registration_details_usertype,
                String registration_details_mail,String registration_details_dob,String registration_details_password){ 
            
        Connection conn=null;
        PreparedStatement pstmt = null;
         Boolean flag = true;
        if (registration_details_fn.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please enter your First Name");
            flag = false;

        }

        if ((registration_details_ln.equals("")) && (flag == true))
        {
            JOptionPane.showMessageDialog(null,"Please enter your Last Name");
            flag = false;  

        }
        
        if ((registration_details_mail.equals("")) && (flag == true))
        {
            JOptionPane.showMessageDialog(null,"Please enter your Email-Id");
               flag = false;

        }
       
        
        if ((registration_details_password.equals("")) && (flag == true))
        {
            JOptionPane.showMessageDialog(null,"Please enter your Password");
              flag = false;

        }
        
         if ((registration_details_usertype.equals("")) && (flag == true))
        {
            JOptionPane.showMessageDialog(null,"Please enter User Type");
               flag = false;

        }

        if( flag == true)
        {
           try{
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");

       
                
               pstmt=conn.prepareStatement("SELECT login_details_pts.Login_details_id_Email FROM fp.login_details_pts\n" +
"where login_details_pts.Login_details_id_Email= ? \n" +
"and login_details_pts.User_Type_Id= ? ;" );
                
                pstmt.setString(1,registration_details_mail);
                pstmt.setString(2,registration_details_usertype);
            
                ResultSet res = pstmt.executeQuery();
            
            if(res.next())
                
            {
                                    Image img = new Image("/image/warning-button-jpg.jpg") ;   
                                    Notifications notificationBuilder = Notifications.create()
                                  .title("Warning")
                                  .text("User Already Exists.Cannot create User")
                                  .graphic(new ImageView(img))
                                  //.graphic(null)
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle(); 
                                  notificationBuilder.show(); 
            }       
            else
                    {
                
					pstmt = conn.prepareStatement("INSERT INTO `login_details_pts` \n" +
"(`Login_details_id_Email`, `Login_details_id_Password`, `User_Type_Id`) \n" +
"VALUES (?,?,?);");
              
                pstmt.setString(1,registration_details_mail);
                pstmt.setString(2,registration_details_password);        
                pstmt.setString(3,registration_details_usertype);

                int i=pstmt.executeUpdate();
                
                if (registration_details_usertype.equals("1"))// admin 
                        {
                pstmt=conn.prepareStatement("INSERT INTO `fp`.`admin_details` "
                        + "(`Admin_Details_FN`, `Admin_Details_LN`, "
                        + "`Admin_Details_DOB`, `User_Type_Id`, `admin_details_email`) "
                        + "VALUES (?,?,?,?,?);");
                
                pstmt.setString(1,registration_details_fn);
                pstmt.setString(2,registration_details_ln);
                pstmt.setString(3,registration_details_dob);
                pstmt.setString(4,registration_details_usertype);
                pstmt.setString(5,registration_details_mail);
                pstmt.executeUpdate();
                }
                
                if (registration_details_usertype.equals("2"))// patient
                        {
                pstmt=conn.prepareStatement("INSERT INTO `fp`.`patient_details` "
                        + "(`patient_Details_FN`, `patient_Details_LN`, "
                        + "`patient_Details_DOB`, `User_Type_Id`, `patient_details_email`) "
                        + "VALUES (?,?,?,?,?);");
                
                pstmt.setString(1,registration_details_fn);
                pstmt.setString(2,registration_details_ln);
                pstmt.setString(3,registration_details_dob);
                pstmt.setString(4,registration_details_usertype);
                pstmt.setString(5,registration_details_mail);
                pstmt.executeUpdate();
                }
                
                
                
                if (registration_details_usertype.equals("3"))// doctor
                        {
                pstmt=conn.prepareStatement("INSERT INTO `fp`.`doctor_details` "
                        + "(`doctor_Details_FN`, `doctor_Details_LN`, "
                        + "`doctor_Details_DOB`, `User_Type_Id`,`doctor_details_email`) "
                        + "VALUES (?,?,?,?,?);");
                
                pstmt.setString(1,registration_details_fn);
                pstmt.setString(2,registration_details_ln);
                pstmt.setString(3,registration_details_dob);
                pstmt.setString(4,registration_details_usertype);
                pstmt.setString(5,registration_details_mail);
                pstmt.executeUpdate();
                }
                
                
                
                if (registration_details_usertype.equals("4"))// lab tech
                        {
                pstmt=conn.prepareStatement("INSERT INTO `fp`.`lab_technician_details` "
                          + "(`lab_technician_details_FN`, `lab_technician_details_LN`, "
                        + "`User_Type_Id`,`lab_technician_details_email`) "
                        + "VALUES (?,?,?,?);");
                
                pstmt.setString(1,registration_details_fn);
                pstmt.setString(2,registration_details_ln);
                pstmt.setString(3,registration_details_usertype);
                pstmt.setString(4,registration_details_mail);
                pstmt.executeUpdate();
                }
                
                
                if(i>0)
                    {
                    
                                    Image img = new Image("/image/small_tick2.png") ;   
                                    Notifications notificationBuilder = Notifications.create()
                                  .title("Information")
                                  .text("User Registered")
                                  .graphic(new ImageView(img))
                                  //.graphic(null)
                                  .hideAfter(Duration.seconds(5))
                                  .position(Pos.BOTTOM_RIGHT);
                                  notificationBuilder.darkStyle();
                                  notificationBuilder.show();     
                }
                
                conn.close();
                        RegistrationController objRegistrationController = new RegistrationController();  
                                objRegistrationController.clearFields();
                    }
            
           }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    }
    
    }
    