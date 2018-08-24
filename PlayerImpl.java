import connectfourclient.Player;
import java.util.ArrayList;
import java.util.Random;

public class PlayerImpl implements Player {
    int iter = 0;

    @Override
    public int nextMove(char var1, char[][] var2) {
        Random rand = new Random();
        return rand.nextInt(7);

    }

    @Override
    public String getTeamName() {
        return "Dragonflies";
    }
}
