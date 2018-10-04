//Package
package Model;

//import statements
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LabRecords {
    
    private StringProperty labpatnm;
    private StringProperty labdocnm;
    private StringProperty labres;
    private StringProperty labrem;

    public LabRecords(StringProperty labpatnm, StringProperty labdocnm, StringProperty labres, StringProperty labrem) {
        this.labpatnm = labpatnm;
        this.labdocnm = labdocnm;
        this.labres = labres;
        this.labrem = labrem;
    }

    /**
     * @return the labpatnm
     */
    public StringProperty getLabpatnm() {
        return labpatnm;
    }

    /**
     * @param labpatnm the labpatnm to set
     */
    public void setLabpatnm(StringProperty labpatnm) {
        this.labpatnm = labpatnm;
    }

    /**
     * @return the labdocnm
     */
    public StringProperty getLabdocnm() {
        return labdocnm;
    }

    /**
     * @param labdocnm the labdocnm to set
     */
    public void setLabdocnm(StringProperty labdocnm) {
        this.labdocnm = labdocnm;
    }

    /**
     * @return the labres
     */
    public StringProperty getLabres() {
        return labres;
    }

    /**
     * @param labres the labres to set
     */
    public void setLabres(StringProperty labres) {
        this.labres = labres;
    }

    /**
     * @return the labrem
     */
    public StringProperty getLabrem() {
        return labrem;
    }

    /**
     * @param labrem the labrem to set
     */
    public void setLabrem(StringProperty labrem) {
        this.labrem = labrem;
    }
    
    

    
    
    


    
}
