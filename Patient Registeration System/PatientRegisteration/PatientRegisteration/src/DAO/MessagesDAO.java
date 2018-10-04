/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.SignInModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Rajesh kumar
 */
public class MessagesDAO extends DBConnection{
    
        
     public ResultSet rs ;

    public ResultSet pageload()        
    {
    
         PreparedStatement pstmt = null;
       // Connection DBConnection.pmartConnection()=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                //Class.forName("com.mysql.jdbc.Driver");
         //       DBConnection.pmartConnection()=DriverManager.getConnection("jdbc:mysql:///patient?characterEncoding=UTF-8&useSSL=false","root","12345678");
                
                
                pstmt=DBConnection.pmartConnection().prepareStatement("select * from \n" +
"(select message_details.message_details_date as dates, CONCAT(patient_details.Patient_Details_FN,\" \",patient_details.Patient_Details_LN) as send, \n" +
"CONCAT(doctor_details.Doctor_Details_FN, \" \", doctor_details.Doctor_Details_LN) as rec,\n" +
"message_details.Message_Details_Text as msg\n" +
"from fp.message_details\n" +
"inner join fp.patient_details on \n" +
"message_details.Message_Details_SID = patient_details.patient_details_id and \n" +
"message_details.Message_Details_sender_Usr_Typ = patient_details.User_type_id\n" +
"inner join fp.Doctor_details on \n" +
"message_details.Message_Details_RID = Doctor_details.Doctor_details_id and \n" +
"message_details.Message_Details_rec_Usr_Typ = Doctor_details.User_type_id) first\n" +
"union all\n" +
"select message_details.message_details_date as dates, CONCAT(doctor_details.Doctor_Details_FN, \" \", doctor_details.Doctor_Details_LN) as send, \n" +
"CONCAT(patient_details.Patient_Details_FN,\" \",patient_details.Patient_Details_LN) as rec, \n" +
"message_details.Message_Details_Text as msg\n" +
"from fp.message_details\n" +
"inner join fp.patient_details on \n" +
"message_details.Message_Details_RID = patient_details.patient_details_id and \n" +
"message_details.Message_Details_REC_Usr_Typ = patient_details.User_type_id\n" +
"inner join fp.Doctor_details on \n" +
"message_details.Message_Details_SID = Doctor_details.Doctor_details_id and \n" +
"message_details.Message_Details_Sender_Usr_Typ = Doctor_details.User_type_id\n" +
"order by dates desc;");
               
                
                  rs = pstmt.executeQuery();
             
    }
        catch(Exception e)
        {
    
        }
       
        return rs;
        
        
    }
    
    
    
     public ResultSet senderload()        
    {
    
         PreparedStatement pstmt = null;
        //Connection DBConnection.pmartConnection()=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
                //Class.forName("com.mysql.jdbc.Driver");
                //DBConnection.pmartConnection()=DriverManager.getConnection("jdbc:mysql:///patient?characterEncoding=UTF-8&useSSL=false","root","12345678");
                
                if (objSignInModel.getUserType().equals("2")){
                pstmt=DBConnection.pmartConnection().prepareStatement("select doctor_details.doctor_details_email from  fp.doctor_details;");                
                }
                else{
                pstmt=DBConnection.pmartConnection().prepareStatement("select patient_details.patient_details_email from fp.patient_details;");
                }
                
                rs = pstmt.executeQuery();
             
    }
        catch(Exception e)
        {
    
        }
       
        return rs;
        
        
    }
     
     
     
     public void send(String message, String receiverMail, String date)        
    {
    
         PreparedStatement pstmt = null;
        //Connection DBConnection.pmartConnection()=null;
        SignInModel objSignInModel = new SignInModel();
        
        try
     {
          //      Class.forName("com.mysql.jdbc.Driver");
            //    DBConnection.pmartConnection()=DriverManager.getConnection("jdbc:mysql:///patient?characterEncoding=UTF-8&useSSL=false","root","12345678");
                
                if (objSignInModel.getUserType().equals("2")){
                pstmt=DBConnection.pmartConnection().prepareStatement("INSERT INTO fp.message_details \n" +
"(Message_Details_Text, Message_Details_SID, Message_Details_RID, Message_Details_Sender_Usr_Typ, \n" +
"Message_Details_Rec_Usr_Typ, message_details_date) \n" +
"VALUES (?, (select patient_details.patient_details_id from fp.patient_details where patient_details.patient_details_email=?) , \n" +
"(select doctor_details.doctor_details_id from fp.doctor_details where doctor_details.doctor_details_email=?) ,\n" +
"'2', '3', ?);");                
                pstmt.setString(1,message);
                pstmt.setString(2,(objSignInModel.getUserEmail()));
                pstmt.setString(3,receiverMail);
                pstmt.setString(4,date);
                }
                else{
                pstmt=DBConnection.pmartConnection().prepareStatement("INSERT INTO fp.message_details \n" +
"(Message_Details_Text, Message_Details_SID, Message_Details_RID, Message_Details_Sender_Usr_Typ, \n" +
"Message_Details_Rec_Usr_Typ, message_details_date) \n" +
"VALUES (?, (select doctor_details.doctor_details_id from fp.doctor_details where doctor_details.doctor_details_email=?) ,\n" +
"(select patient_details.patient_details_id from fp.patient_details where patient_details.patient_details_email=?) , \n" +
"'3', '2', ?);");
                pstmt.setString(1,message);
                pstmt.setString(2,(objSignInModel.getUserEmail()));
                pstmt.setString(3,receiverMail);
                pstmt.setString(4,date);
                }
                
                pstmt.executeUpdate();
             
    }
        catch(Exception e)
        {
    
        }
       
        
    }
}
