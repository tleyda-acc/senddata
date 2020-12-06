import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public void runServer() {
        try {
            server = new ServerSocket(5555);
            System.out.println("Waiting for client...");
            socket = server.accept();
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            boolean done = false;

            while(!done) {
                Message message = (Message)input.readObject();

                if (message.getType().equals("done")) {
                    done = true;
                } else {
                    if (message.getType().equals("string")) {
                        StringMessage stringMessage = (StringMessage) message;
                        System.out.println("String message: " + stringMessage.getValue());
                        IntMessage intMessage = new IntMessage(stringMessage.getValue().length());
                        output.writeObject(intMessage);
                        output.flush();
                    } else if (message.getType().equals("integer")) {
                        IntMessage intMessage = (IntMessage) message;
                        System.out.println("Int message: " + intMessage.getValue());
                        StringMessage strMessage = new StringMessage("You sent the number: " + intMessage.getValue());
                        output.writeObject(strMessage);
                        output.flush();
                    }
                }
            }

            input.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().runServer();
    }
}
