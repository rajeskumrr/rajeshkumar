//Package
package validatetextfields;

//import statements
import javafx.scene.control.TextField;

/**
 * Classes are created to check for only characters are entered.
 */
public class FirstNameRegTextField extends TextField{
    
    public FirstNameRegTextField(){
        this.setPromptText("Enter First Name");
    } 
    
    public void replaceText(int i,int il,String string)
    {
        if(string.matches("[a-zA-Z]")|| string.isEmpty()){
         super.replaceText(il, il, string);   
        }
        
    }
    
    public void replaceSelection(String string)
    {
        super.replaceSelection(string);
    }
          
}
