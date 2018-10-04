//Package
package Model;


public class SignInModel {
    
     public static String userEmail;
     public static String usertype; // = (String) userType.getSelectionModel().getSelectedItem();
     public String password ; //;//= dob.getEditor().getText();
    
    public String getUserEmail() {
		return userEmail;
	}

    public void setFn(String userEmail) {
		this.userEmail = userEmail;
	}
        
    public String getPassword() {
		return password;
	}

    public void setPassword(String password) {
		this.password = password;
	}
    
     public String getUserType() {
		return usertype;
	}

    public void setUserType(String usertype) {
        if (usertype.equals("Admin"))
            usertype = "1";
        else if (usertype.equals("Patient"))
            usertype = "2";
        else if (usertype.equals("Doctor"))
            usertype = "3";
        else if (usertype.equals("Lab Technician"))
            usertype = "4";
		this.usertype = usertype;
	}
    
    
}
