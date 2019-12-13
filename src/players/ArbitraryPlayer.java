package players;

public class ArbitraryPlayer extends GenericPlayer{

    public ArbitraryPlayer(String name) {
        this.name = name;
    }

    @Override
    public boolean getAction() {
        return Math.random() > 0.5;
    }

    @Override
    public int getBet() {
        return (int) ((Math.random() * (super.getBalance()))) + 1;
    }

    @Override
    public String getFirstMove() {
        //TODO: Implement first move controls
        return null;
    }
}
