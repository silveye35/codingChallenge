import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/* telnet ${IP} ${SERVER SOCKET}*/

public class Client {
    private Socket socket = null;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;

    public Client(String serverName, int serverPort) {
        System.out.println("Establishing connection. Please wait....");
        try {
            this.socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + this.socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }

        int iterations = 1;
        int number;
        while(iterations <= 50){
            try {
                String serverInput = streamIn.readUTF();

                System.out.println("server input is " + serverInput);

                number = Integer.parseInt(serverInput) + 1;
                this.streamOut.writeUTF(String.valueOf(number));
                System.out.println("send");
                this.streamOut.flush();

                System.out.println(iterations + ": " + String.valueOf(number));

                iterations++;
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }

    }

    public void start() throws IOException {
        this.streamIn = new DataInputStream(this.socket.getInputStream());
        this.streamOut = new DataOutputStream(this.socket.getOutputStream());
    }

    public void stop() throws IOException {
        try {
            if (this.streamIn != null) this.streamIn.close();
            if (this.streamOut != null) this.streamOut.close();
            if (this.socket != null) this.socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }

    public static void main(String[] args) {
        Client client = new Client("139.126.184.47",9090);
    }
}
