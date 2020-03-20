import blackjack.BasicBlackJack;
import blackjack.BlackJack;
import blackjack.CasinoBlackJack;
import players.ArbitraryPlayer;
import players.CardCounter;
import players.GenericPlayer;
import players.Human;
import players.StrategicPlayer;

import java.util.ArrayList;
import java.util.List;

public class TestingClient {
    public static void main(String[] args){
        int deckCount = 8;
        int arbitraryPlayerCount = 0;
        int strategicPlayerCount = 1;
        int counterCount = 0;
        boolean hasHumanPlayer = false;
        List<GenericPlayer> players = new ArrayList<>();
        for (int i = 0; i < arbitraryPlayerCount; i++) {
            players.add(new ArbitraryPlayer("aPlayer_" + i));
        }
        for (int i = 0; i < strategicPlayerCount; i++) {
            players.add(new StrategicPlayer("sPlayer_" + i));
        }
        for (int i = 0; i < counterCount; i++) {
            players.add(new CardCounter("Counter_" + i, 50));
        }
        if (hasHumanPlayer) {
            players.add(new Human());
        }
        //playUntilComplete(deckCount, players);
        //playFinite(deckCount, 10);
        profitRate(deckCount, players, 10);

    }
    private static void playUntilComplete(int deckCount, List<GenericPlayer> players){
        BlackJack game = new CasinoBlackJack(deckCount, new ArrayList<>(players));
        while (game.canPlay()) {
            game.playRound();
        }
        System.out.println();
        int overallProfit = 0;
        for (GenericPlayer p : players) {
            int winPercent = (int) (p.getWinRate() * 100);
            System.out.println(
                    p.getName() + " won " + p.getRoundsWon()
                            + " rounds out of "
                            + p.getRoundsPlayed()
                            + " (" + winPercent + "%)");
            System.out.println(p.getName() + " has $" + p.getBalance() + " left");
            overallProfit += p.getBalance();
            overallProfit -= 10000;
        }
        System.out.println("Overall profit: $" + overallProfit);
    }

    private static void profitRate(int deckCount, List<GenericPlayer> players, int playCount) {
        int profitCount = 0;
        int profit = 0;
        for (int i = 0; i < playCount; i++) {
            BlackJack game = new CasinoBlackJack(deckCount, new ArrayList<>(players));
            while (game.canPlay()) {
                game.playRound();
            }
            System.out.println();
            int overallProfit = 0;
            for (GenericPlayer p : players) {
                int winPercent = (int) (p.getWinRate() * 100);
                System.out.println(
                        p.getName() + " won " + p.getRoundsWon()
                                + " rounds out of "
                                + p.getRoundsPlayed()
                                + " (" + winPercent + "%)");
                System.out.println(p.getName() + " has $" + p.getBalance() + " left");
                overallProfit += p.getBalance();
                overallProfit -= 10000;
            }
            for (GenericPlayer p : players) {
                p.changeBalance(0 - p.getBalance());
                p.changeBalance(10000);
                p.clearHand();
            }
            profit += overallProfit;
            System.out.println("Overall profit: $" + overallProfit);
            if (overallProfit > 0) {
                profitCount++;
            }
        }
        System.out.println("Player profited " + profitCount + " times (" + (profitCount * 100 / playCount) +
                "%)");
        System.out.println("Player profited $" + profit + " (" + (profit * 100 / 10000) + "%)");
        System.out.println("Player averaged $" + profit / playCount + " per game");
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
            System.out.println(p.getName() + " has $" + p.getBalance() + " left");
        }
    }
}
