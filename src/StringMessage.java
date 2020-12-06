public class StringMessage extends Message {
    String value;
    StringMessage(String messageValue) {
        super("string");
        value = messageValue;
    }

    public String getValue(){
        return value;
    }
}
