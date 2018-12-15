package bot;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class BlackJack extends AbstractGame{

    private Pack pack;
    private Hand hand;
    private Set<Card> playerHand = new HashSet<>();
    private Set<Card> botHand = new HashSet<>();
    private ArrayList<String> command;
    private int sufficientAmount = 17;

    BlackJack() {
        hand = new Hand();
        pack = new Pack();
        command = getCommands();
        returnCommandsOfGame();
    }

    private static int getHandScore(Set<Card> hand) {
        return hand.stream().mapToInt(Card::getScore).sum();
    }
    
    @Override
    public void start() {
        botHand.addAll(pack.pickMany(2));
        playerHand.addAll(pack.pickMany(2));

        while(getHandScore(botHand) <= sufficientAmount) {
            botHand.add(pack.pick());
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
        playerHand.add(pack.pick());
    }

    public void addCard() {
        addCardForPlayer();
    }

    public Set<Card> getPlayerHand() {
        return playerHand;
    }

    public int returnHandScore(Set<Card> userHand) {
        return getHandScore(userHand);
    }

    public Set<Card> getBotHand() {
        return botHand;
    }

    public void setBotHand(Set<Card> hand) {
        botHand = hand;
    }

    public void setPlayerHand(Set<Card> hand) {
        playerHand = hand;
    }
}
