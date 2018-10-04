//Package
package Model;

//import statements
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class MessagesModel {
    
    private final StringProperty date;
    private final StringProperty sender;
    private final StringProperty receiver;
    private final StringProperty messages;

//Constructor    
    public MessagesModel(String date, String sender, String receiver, String messages)
    {
        this.date = new SimpleStringProperty(date);
        this.sender = new SimpleStringProperty(sender);
        this.receiver = new SimpleStringProperty(receiver);
        this.messages = new SimpleStringProperty(messages);
    }
     public StringProperty dateProperty()
    {
        return date;
    }
     public StringProperty senderProperty()
    {
        return sender;
    }
     public StringProperty receiverProperty()
    {
        return receiver;
    }
     public StringProperty messagesProperty()
    {
        return messages;
    }
    
}
