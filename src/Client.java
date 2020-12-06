import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket  = null;
    ObjectOutputStream output = null;

    public void runClient() {
        try {
            socket = new Socket("127.0.0.1",  5555);
            output = new ObjectOutputStream(socket.getOutputStream());

            IntMessage intMessage = new IntMessage(22);
            output.writeObject(intMessage);
            output.flush();

            StringMessage strMessage = new StringMessage("Check it out!");
            output.writeObject(strMessage);
            output.flush();

            Message done = new Message("done");
            output.writeObject(done);
            output.flush();

            output.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().runClient();
    }
}
