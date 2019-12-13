import players.ArbitraryPlayer;
import players.GenericPlayer;
import players.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class TestingClient {
    public static void main(String[] args){
        int deckCount = 100;
        int playerCount = 0;
        List<GenericPlayer> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new ArbitraryPlayer("aPlayer_" + i));
        }
        players.add(new HumanPlayer());
        playUntilComplete(deckCount, players);
        //playFinite(deckCount, 10);
    }
    private static void playUntilComplete(int deckCount, List<GenericPlayer> players){
        BlackJackGame game = new BlackJackGame(deckCount, players);
        while (game.canPlay()) {
            game.playRound();
        }
    }

    private static void playFinite(int deckCount, int playCount, List<GenericPlayer> players){
        BlackJackGame game = new BlackJackGame(deckCount, players);
        for (int i = 0; i < playCount; i++) {
            game.playRound();
        }
    }
}
