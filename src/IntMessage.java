public class IntMessage extends Message {
    int value;
    IntMessage(int messageValue) {
        super("integer");
        value = messageValue;
    }

    public int getValue() {
        return value;
    }
}
