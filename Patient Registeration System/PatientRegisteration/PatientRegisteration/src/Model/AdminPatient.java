//Package
package Model;

///Import statements
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class AdminPatient {
    
    private String pdid;
    private String pdfnm;
    private String pdlnm;
    private String pdgen;
    private String pdadd;
    private String pdcont; 
    private String pddob;
    private String pduserid;
    private String pdemail;

    //Contructor
    public AdminPatient(String pdid, String pdfnm, String pdlnm, String pdgen, String pdadd, String pdcont, String pddob, String pduserid, String pdemail) {
        this.pdid = pdid;
        this.pdfnm = pdfnm;
        this.pdlnm = pdlnm;
        this.pdgen = pdgen;
        this.pdadd = pdadd;
        this.pdcont = pdcont;
        this.pddob = pddob;
        this.pduserid = pduserid;
        this.pdemail = pdemail;
    }

    /**
     * @return the pdid
     */
    public String getPdid() {
        return pdid;
    }

    /**
     * @param pdid the pdid to set
     */
    public void setPdid(String pdid) {
        this.pdid = pdid;
    }

    /**
     * @return the pdfnm
     */
    public String getPdfnm() {
        return pdfnm;
    }

    /**
     * @param pdfnm the pdfnm to set
     */
    public void setPdfnm(String pdfnm) {
        this.pdfnm = pdfnm;
    }

    /**
     * @return the pdlnm
     */
    public String getPdlnm() {
        return pdlnm;
    }

    /**
     * @param pdlnm the pdlnm to set
     */
    public void setPdlnm(String pdlnm) {
        this.pdlnm = pdlnm;
    }

    /**
     * @return the pdgen
     */
    public String getPdgen() {
        return pdgen;
    }

    /**
     * @param pdgen the pdgen to set
     */
    public void setPdgen(String pdgen) {
        this.pdgen = pdgen;
    }

    /**
     * @return the pdadd
     */
    public String getPdadd() {
        return pdadd;
    }

    /**
     * @param pdadd the pdadd to set
     */
    public void setPdadd(String pdadd) {
        this.pdadd = pdadd;
    }

    /**
     * @return the pdcont
     */
    public String getPdcont() {
        return pdcont;
    }

    /**
     * @param pdcont the pdcont to set
     */
    public void setPdcont(String pdcont) {
        this.pdcont = pdcont;
    }

    /**
     * @return the pddob
     */
    public String getPddob() {
        return pddob;
    }

    /**
     * @param pddob the pddob to set
     */
    public void setPddob(String pddob) {
        this.pddob = pddob;
    }

    /**
     * @return the pduserid
     */
    public String getPduserid() {
        return pduserid;
    }

    /**
     * @param pduserid the pduserid to set
     */
    public void setPduserid(String pduserid) {
        this.pduserid = pduserid;
    }

    /**
     * @return the pdemail
     */
    public String getPdemail() {
        return pdemail;
    }

    /**
     * @param pdemail the pdemail to set
     */
    public void setPdemail(String pdemail) {
        this.pdemail = pdemail;
    }

    
    
}
