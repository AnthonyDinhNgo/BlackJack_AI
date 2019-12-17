package players;

import deck.Hand;

public class CardCounter extends GenericPlayer {
    @Override
    public int getBet() {
        return 0;
    }

    @Override
    public String getFirstMove() {
        return null;
    }

    @Override
    public boolean getSplit(Hand h) {
        return false;
    }

    @Override
    public boolean getAction(Hand h) {
        return false;
    }

    @Override
    public boolean getAction() {
        return false;
    }
}
