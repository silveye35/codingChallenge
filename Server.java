import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/*http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html*/

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;


    public Server(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait...");
            this.server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            System.out.println("waiting for a client....");
            this.socket = server.accept();
            System.out.println("Client accepted: " + this.socket);
            open();
            start();

            Random rand = new Random();
            String clientInput = "";
            int random = rand.nextInt(50) + 1;

            this.streamOut.writeUTF(String.valueOf(random));
            System.out.println("send");
            this.streamOut.flush();


            int number;
            int iterations = 1;
            while (iterations <= 50) {
                try {
                    clientInput = this.streamIn.readUTF();
                    System.out.println(iterations + ": " + clientInput);

                    this.streamOut.writeUTF(clientInput);
                    System.out.println("send");
                    this.streamOut.flush();

                    iterations++;
                } catch (IOException ioe) {
                    iterations = 51;
                    System.out.println("IOE");
                }
            }
            close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void start() throws IOException {
        this.streamIn = new DataInputStream(this.socket.getInputStream());
        this.streamOut = new DataOutputStream(this.socket.getOutputStream());
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (streamIn != null) streamIn.close();
    }

    public static void main(String[] args) {
        Server server = new Server(9090);
    }

}
