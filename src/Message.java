import java.io.Serializable;

public class Message implements Serializable {
    private String type;

    Message(String messageType) {
        type = messageType;
    }

    public String getType() {
        return type;
    }
}
