//Package
package DAO;

//Import Statements
import Model.DetailsModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * In this class, the login details are selected from the database and it checked, based on which it allows the user to login.
 * @author SEAN GEORGE
 */
public class SignInDAO {
    
    public int dataBase(String registration_details_usertype,
                String registration_details_mail ,String registration_details_password){ 
        int ret = 0;
      Connection conn=null;
        PreparedStatement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
            
            // Statement stmt = conn.createStatement();
            
            stmt=conn.prepareStatement("SELECT login_details_pts.Login_details_id_Email FROM fp.login_details_pts\n" +
"where login_details_pts.Login_details_id_Email=? and\n" +
"login_details_pts.Login_details_id_Password=? and\n" +
"login_details_pts.User_Type_Id = ?;" );                                  
            stmt.setString(1,registration_details_mail);
            stmt.setString(2,registration_details_password);
            stmt.setString(3,registration_details_usertype);
            
           ResultSet res = stmt.executeQuery();

            if(res.next() == true)
            {                
                 ret  = 1;
                
            }   else
            {
                 ret  = 0;
            }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    return ret;
}
}