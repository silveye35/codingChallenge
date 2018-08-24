import connectfourclient.Connect4Client;
import connectfourclient.Player;

public class Dragonflies {
    public static void main(String[] arg) {
        Player player = new PlayerImpl();

        Connect4Client client = new Connect4Client("139.126.184.118", 9032, player);
        client.turnOnDebugging();
        client.start();
    }
}
