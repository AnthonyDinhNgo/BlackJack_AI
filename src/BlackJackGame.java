import deck.Deck;
import players.Dealer;
import players.GenericPlayer;

import java.util.Collections;
import java.util.List;

public class BlackJackGame {
    private Deck deck;
    private Dealer dealer;
    private List<GenericPlayer> playerRoster;

    public BlackJackGame(int deckCount, List<GenericPlayer> playerRoster){
        if (playerRoster == null) {
            throw new IllegalArgumentException("playerRoster cannot be null");
        }
        if (playerRoster.isEmpty()) {
            throw new IllegalArgumentException("playerRoster must have at least one element");
        }
        deck = new Deck(deckCount);
        this.playerRoster = playerRoster;
        dealer = new Dealer();
    }

    public void playRound(){
        System.out.println("\n");
        //Distributing player cards
        for (GenericPlayer p : playerRoster) {
            p.giveCard(deck.getCard());
        }
        for (GenericPlayer p : playerRoster) {
            p.giveCard(deck.getCard());
            System.out.println(p.getName() + " has " + p.getHand().toString());
            System.out.println(p.getName() + " has possible values of " + p.getHand().value());
        }
        //Distributing dealer cards
        dealer.giveCard(deck.getCard());
        dealer.giveCard(deck.getCard());
        System.out.println("Dealer has [] and " +
                dealer.getHand().getCards().get(1));

        // PlayerActions

        //Dealer Action
        while (dealer.getAction()){
            dealer.giveCard(deck.getCard());
        }
        //Dealer Conclusion
        System.out.println("Dealer has " + dealer.getHand().toString());
        if (dealer.getHand().value().isEmpty()){
            System.out.println("Dealer has busted");
        } else {
            System.out.println("Dealer has a " + Collections.max(dealer.getHand().value()));
        }
        for (GenericPlayer p: playerRoster) {
            System.out.println(p.getName() + " has " + p.getHand().toString());
            if (p.getHand().value().isEmpty()){
                System.out.println(p.getName() + " has busted");
            } else {
                System.out.println(p.getName() + " has a " + Collections.max(p.getHand().value()));
            }
        }
        //Clearing all hands
        for (GenericPlayer p: playerRoster) {
            p.clearHand();
        }
        dealer.clearHand();
    }

    public boolean canPlay(){
        return deck.size() > 2 * (playerRoster.size() + 1);
    }
}
