//Package
package Controller;

//Import statements
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 * In this class maps configuration is done.
 */
public class WebUIController implements Initializable {

   
    @FXML
    private WebView webView;
    private WebEngine webEngine;

      
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        webEngine.load("https://www.google.com/maps/search/hospitals+in+chicago/@41.874687,-87.6161673,11.62z?hl=en");
    }
}
