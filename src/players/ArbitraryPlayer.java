package players;

public class ArbitraryPlayer extends GenericPlayer{

    public ArbitraryPlayer(String name) {
        this.name = name;
    }

    @Override
    public boolean getAction() {
        return Math.random() > 0.5;
    }
}
