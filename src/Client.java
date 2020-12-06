import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket  = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;

    public void runClient() {
        try {
            socket = new Socket("127.0.0.1",  5555);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            IntMessage intMessage = new IntMessage(22);
            output.writeObject(intMessage);
            output.flush();

            getMessage();

            StringMessage strMessage = new StringMessage("Check it out!");
            output.writeObject(strMessage);
            output.flush();

            getMessage();

            Message done = new Message("done");
            output.writeObject(done);
            output.flush();

            output.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getMessage() throws IOException, ClassNotFoundException {
        Message message = (Message)input.readObject();

        if (message.getType().equals("string")) {
            StringMessage stringMessage = (StringMessage) message;
            System.out.println("String message: " + stringMessage.getValue());
        } else if (message.getType().equals("integer")) {
            IntMessage intMessage = (IntMessage) message;
            System.out.println("Int message: " + intMessage.getValue());
        }
    }

    public static void main(String[] args) {
        new Client().runClient();
    }
}
