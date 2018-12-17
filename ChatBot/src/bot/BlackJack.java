package bot;
import java.util.ArrayList;
import java.util.Set;


public class BlackJack extends AbstractGame{

    private Pack pack;
    private Hand playerHand;
    private Hand botHand;
    private ArrayList<String> command;
    private int sufficientAmount = 17;

    BlackJack() {
        pack = new Pack();
        command = getCommands();
        returnCommandsOfGame();
    }
    
    @Override
    public void start() {
        botHand = new Hand(pack.pickMany(2));
        playerHand = new Hand(pack.pickMany(2));

        while(botHand.getSum() <= sufficientAmount) {
            botHand.addCard(pack.pick());
        }
    }

    private void returnCommandsOfGame() {
        command.add("start");
        command.add("more");
        command.add("stop");
        command.add("end");
        command.add("statistic");
        command.add("help");
        setCommands(command);
    }

    private void addCardForPlayer() {
        playerHand.addCard(pack.pick());
    }

    public void addCard() {
        addCardForPlayer();
    }

    public void setPlayerHand(Set<Card> card) {
        playerHand = new Hand(card);
    }

    public Set<Card> getPlayerHand() {
        return playerHand.getCards();
    }

    public Set<Card> getBotHand() {
        return botHand.getCards();
    }
}
