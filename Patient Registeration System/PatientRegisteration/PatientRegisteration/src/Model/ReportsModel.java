//Package
package Model;

//import statements
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportsModel {
    
    private String pname;
    private  String remark;
    private String result;
    
    //Constructor
    public ReportsModel(String pname, String result, String remark)
    {
        this.pname = pname;
        this.result = result;
        this.remark = remark;
        
    }

    /**
     * @return the pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname the pname to set
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }
  
}    
