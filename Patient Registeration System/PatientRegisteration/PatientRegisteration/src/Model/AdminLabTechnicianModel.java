//Pakage
package Model;

public class AdminLabTechnicianModel {
    
    private String labtechdetid;
    private String labtechdetfnm;
    private String labtechdetlnm;
    private String labtechdetadd;
    private String labtechdetcont;
    private String labtechdetemail;
    
    
     //Contructor
    public AdminLabTechnicianModel(String labtechdetid, String labtechdetfnm, String labtechdetlnm, String labtechdetadd, String labtechdetcont, String labtechdetemail) {
        this.labtechdetid = labtechdetid;
        this.labtechdetfnm = labtechdetfnm;
        this.labtechdetlnm = labtechdetlnm;
        this.labtechdetadd = labtechdetadd;
        this.labtechdetcont = labtechdetcont;
        this.labtechdetemail = labtechdetemail;
    }

    /**
     * @return the labtechdetid
     */
    public String getLabtechdetid() {
        return labtechdetid;
    }

    /**
     * @param labtechdetid the labtechdetid to set
     */
    public void setLabtechdetid(String labtechdetid) {
        this.labtechdetid = labtechdetid;
    }

    /**
     * @return the labtechdetfnm
     */
    public String getLabtechdetfnm() {
        return labtechdetfnm;
    }

    /**
     * @param labtechdetfnm the labtechdetfnm to set
     */
    public void setLabtechdetfnm(String labtechdetfnm) {
        this.labtechdetfnm = labtechdetfnm;
    }

    /**
     * @return the labtechdetlnm
     */
    public String getLabtechdetlnm() {
        return labtechdetlnm;
    }

    /**
     * @param labtechdetlnm the labtechdetlnm to set
     */
    public void setLabtechdetlnm(String labtechdetlnm) {
        this.labtechdetlnm = labtechdetlnm;
    }

    /**
     * @return the labtechdetadd
     */
    public String getLabtechdetadd() {
        return labtechdetadd;
    }

    /**
     * @param labtechdetadd the labtechdetadd to set
     */
    public void setLabtechdetadd(String labtechdetadd) {
        this.labtechdetadd = labtechdetadd;
    }

    /**
     * @return the labtechdetcont
     */
    public String getLabtechdetcont() {
        return labtechdetcont;
    }

    /**
     * @param labtechdetcont the labtechdetcont to set
     */
    public void setLabtechdetcont(String labtechdetcont) {
        this.labtechdetcont = labtechdetcont;
    }

    /**
     * @return the labtechdetemail
     */
    public String getLabtechdetemail() {
        return labtechdetemail;
    }

    /**
     * @param labtechdetemail the labtechdetemail to set
     */
    public void setLabtechdetemail(String labtechdetemail) {
        this.labtechdetemail = labtechdetemail;
    }

   
    
    
    
}