import deck.Deck;
import players.Dealer;
import players.GenericPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {
    private Deck deck;
    private Dealer dealer;
    private List<GenericPlayer> playerRoster;

    public BlackJackGame(int deckCount){
        deck = new Deck(deckCount);
        playerRoster = new ArrayList<>();
        dealer = new Dealer();
    }

    public void playRound(){
        //Give playerRoster cards
        dealer.giveCard(deck.getCard());
        dealer.giveCard(deck.getCard());
        System.out.println("Dealer has [] and " +
                dealer.getHand().getCards().get(1));
        // PlayerActions
        while (dealer.getAction()){
            dealer.giveCard(deck.getCard());
        }
        System.out.println("Dealer has " + dealer.getHand().toString());
        if (dealer.getHand().value().isEmpty()){
            System.out.println("Dealer busted");
        } else {
            System.out.println("Dealer has a " + Collections.max(dealer.getHand().value()));
        }
        dealer.clearHand();
    }
}
