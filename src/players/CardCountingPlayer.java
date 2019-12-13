package players;

public class CardCountingPlayer extends GenericPlayer{

    public CardCountingPlayer(String name) {
        super.name = name;
    }

    @Override
    public int getBet() {
        return 0;
    }

    @Override
    public boolean getAction() {
        return false;
    }
}
