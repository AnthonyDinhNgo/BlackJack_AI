public class TestingClient {
    public static void main(String[] args){
        BlackJackGame game = new BlackJackGame(6);
        int playCount = 10;
        for (int i = 0; i < playCount; i++) {
            game.playRound();
        }
    }
}
