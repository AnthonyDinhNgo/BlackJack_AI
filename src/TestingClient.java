import blackjack.BasicBlackJack;
import blackjack.BlackJack;
import players.ArbitraryPlayer;
import players.GenericPlayer;
import players.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class TestingClient {
    public static void main(String[] args){
        int deckCount = 1000;
        int playerCount = 1;
        boolean hasHumanPlayer = false;
        List<GenericPlayer> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new ArbitraryPlayer("aPlayer_" + i));
        }
        if (hasHumanPlayer) {
            players.add(new HumanPlayer());
        }
        playUntilComplete(deckCount, players);
        //playFinite(deckCount, 10);
    }
    private static void playUntilComplete(int deckCount, List<GenericPlayer> players){
        BlackJack game = new BasicBlackJack(deckCount, new ArrayList<>(players));
        while (game.canPlay()) {
            game.getMetrics();
        }
        System.out.println();
        for (GenericPlayer p : players) {
            int winPercent = (int) (p.getWinRate() * 100);
            System.out.println(
                    p.getName() + " won " + p.getRoundsWon()
                            + " rounds out of "
                            + p.getRoundsPlayed()
                            + " (" + winPercent + "%)");
        }
    }

    private static void playFinite(int deckCount, int playCount, List<GenericPlayer> players){
        BasicBlackJack game = new BasicBlackJack(deckCount, players);
        for (int i = 0; i < playCount; i++) {
            game.playRound();
        }
        System.out.println();
        for (GenericPlayer p : players) {
            int winPercent = (int) (p.getWinRate() * 100);
            System.out.println(
                    p.getName() + " won " + p.getRoundsWon()
                            + " rounds out of "
                            + p.getRoundsPlayed()
                            + " (" + winPercent + "%)");
        }
    }
}
