/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Rajesh kumar
 */
public class AppointmentsModel {
    
      private final StringProperty docname;
    private final StringProperty patname;
    private final StringProperty dates;
    private final StringProperty status;
    private final StringProperty time;
    private final StringProperty id;
    public static String aptId;
    
    public AppointmentsModel(String docname, String patname, String dates, String status, String time, String id)
    {
        this.docname = new SimpleStringProperty(docname);
        this.patname = new SimpleStringProperty(patname);
        this.dates = new SimpleStringProperty(dates);
        this.status = new SimpleStringProperty(status);
        this.time = new SimpleStringProperty(time);
        this.id = new SimpleStringProperty(id);
    }
     public StringProperty datesProperty()
    {
        return dates;
    }
          public StringProperty idProperty()
    {
        return id;
    }
     public StringProperty docnameProperty()
    {
        return docname;
    }
     public StringProperty patnameProperty()
    {
        return patname;
    }
     public StringProperty statusProperty()
    {
        return status;
    }
    
     public StringProperty timeProperty()
    {
        return time;
    }
     
     public String getID(){
     AppointmentsModel.aptId = id.getValue().toString();
         return aptId;
     }
    
}
