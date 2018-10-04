//Package
package Model;


public class RegistrationModel {
  
        private String registration_details_fn; // = firstname.getText();
        private String registration_details_ln; // = lastname.getText();
        private String registration_details_usertype; // = (String) userType.getSelectionModel().getSelectedItem();
        private String registration_details_mail ; //= emailid.getText();
        private String registration_details_password ; //;//= dob.getEditor().getText();
    
        
        public String getFn() {
		return registration_details_fn;
	}

    public void setFn(String registration_details_fn) {
		this.registration_details_fn = registration_details_fn;
	}

    public String getLn() {
		return registration_details_ln;
	}

    public void setLn(String registration_details_ln) {
		this.registration_details_ln = registration_details_ln;
	}
    
    public String getUserType() {
		return registration_details_usertype;
	}

    public void setUserType(String registration_details_usertype) {
        if (registration_details_usertype.equals("Admin"))
            registration_details_usertype = "1";
        else if (registration_details_usertype.equals("Patient"))
            registration_details_usertype = "2";
        else if (registration_details_usertype.equals("Doctor"))
            registration_details_usertype = "3";
        else if (registration_details_usertype.equals("Lab Technician"))
            registration_details_usertype = "4";
		this.registration_details_usertype = registration_details_usertype;
	}
    
    public String getMail() {
		return registration_details_mail;
	}

    public void setMail(String registration_details_mail) {
		this.registration_details_mail = registration_details_mail;
	}
    
    public String getPass() {
		return registration_details_password;
	}

    public void setPass(String registration_details_password) {
		this.registration_details_password = registration_details_password;
	}
    
}
