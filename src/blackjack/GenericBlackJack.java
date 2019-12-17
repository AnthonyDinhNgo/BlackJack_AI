package blackjack;

import deck.Deck;
import players.Dealer;
import players.GenericPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericBlackJack implements BlackJack {

    List<GenericPlayer> playerRoster;
    Deck deck;
    Dealer dealer = new Dealer();
    Map<GenericPlayer, Integer> betMap = new HashMap<>();

    public abstract void playRound();

    void obtainBets() {
        for (GenericPlayer p : playerRoster) {
            int bet = p.getBet();
            p.changeBalance(0-bet);
            betMap.put(p, bet);
            System.out.println(p.getName() + " has bet $" + bet);
        }
    }

    void eliminate(List<GenericPlayer> eliminationList) {
        for (GenericPlayer p : eliminationList) {
            playerRoster.remove(p);
            System.out.println(p.getName() + " has no more money and has left the game");
            int winPercent = (int) (p.getWinRate() * 100);
            System.out.println(p.getName() + " won " + p.getRoundsWon() + " rounds (" + winPercent + "%)");
        }
    }

    public boolean canPlay(){
        return deck.size() > 2 * (playerRoster.size() + 1) && playerRoster.size() > 0;
    }
}
