//Package
package Model;

import javafx.scene.control.Toggle;


public class DetailsModel {
    
    public static String userEmail;
    
        private String patient_details_full_name;
        private String patient_details_gender;
        private String patient_details_address;
        private String patient_details_city;
        private String patient_details_state;
        private String patient_details_postal_code;
        private String patient_details_status;
        
            
        public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
        
        public String getUserEmail() {
		return userEmail;
	}
        
        public String getFn() {
		return patient_details_full_name;
	}

    public void setFn(String patient_details_full_name) {
		this.patient_details_full_name = patient_details_full_name;
	}

    public String getGen() {
		return patient_details_gender;
	}

    public void setGen(String patient_details_gender) {
		this.patient_details_gender = patient_details_gender;
	}
    
    public String getAdd() {
		return patient_details_address;
	}

    public void setAdd(String patient_details_address) {
		this.patient_details_address = patient_details_address;
	}
    
    public String getCity() {
		return patient_details_city;
	}

    public void setCity(String patient_details_city) {
		this.patient_details_city = patient_details_city;
	}
    
    public String getState() {
		return patient_details_state;
	}

    public void setState(String patient_details_state) {
		this.patient_details_state = patient_details_state;
	}
    
    public String getPc() {
		return patient_details_postal_code;
	}

    public void setPc(String patient_details_postal_code) {
		this.patient_details_postal_code = patient_details_postal_code;
	}
    
     public String getSt() {
		return patient_details_status;
	}

    public void setSt(String patient_details_status) {
		this.patient_details_status = patient_details_status;
	}
    
}
