//Pakage
package Model;


public class AdminDoctorModel {
    
    private String did;
    private String dfnm;
    private String dlnm;
    private String dspeciality;    
    private String dregid;
    private String demail;

//Contructor
    public AdminDoctorModel(String did, String dfnm, String dlnm,String dspeciality, String dregid,String demail) 
    {
        this.did = did;
        this.dfnm = dfnm;
        this.dlnm = dlnm;
        this.dspeciality = dspeciality;
        this.dregid = dregid;
        this.demail = demail;
    }

    /**
     * @return the did
     */
    public String getDid() {
        return did;
    }

    /**
     * @param did the did to set
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * @return the dfnm
     */
    public String getDfnm() {
        return dfnm;
    }

    /**
     * @param dfnm the dfnm to set
     */
    public void setDfnm(String dfnm) {
        this.dfnm = dfnm;
    }

    /**
     * @return the dlnm
     */
    public String getDlnm() {
        return dlnm;
    }

    /**
     * @param dlnm the dlnm to set
     */
    public void setDlnm(String dlnm) {
        this.dlnm = dlnm;
    }

    /**
     * @return the dspeciality
     */
    public String getDspeciality() {
        return dspeciality;
    }

    /**
     * @param dspeciality the dspeciality to set
     */
    public void setDspeciality(String dspeciality) {
        this.dspeciality = dspeciality;
    }

    /**
     * @return the dregid
     */
    public String getDregid() {
        return dregid;
    }

    /**
     * @param dregid the dregid to set
     */
    public void setDregid(String dregid) {
        this.dregid = dregid;
    }

    /**
     * @return the demail
     */
    public String getDemail() {
        return demail;
    }

    /**
     * @param demail the demail to set
     */
    public void setDemail(String demail) {
        this.demail = demail;
    }

   
    

    
    
}
