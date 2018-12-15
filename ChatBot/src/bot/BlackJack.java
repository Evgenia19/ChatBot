package bot;
import java.util.*;


public class BlackJack extends AbstractGame{

    private Pack pack;
    private Set<Card> playerHand = new HashSet<>();
    private Set<Card> botHand = new HashSet<>();

    public int result = 0;
    private ArrayList<String> command;
    private String say21 = "";
    private String more21 = "";
    private String less21 = "";
    private int sufficientAmount = 17;
    private int winScore = 21;

    BlackJack() {
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

    public String getMessageOfGameForPlayer() {
        return "You card: " + StringHelpers.join(' ', playerHand) + "\n" + "result sum: " + getHandScore(playerHand);
    }

    public String addCard() {
        playerHand.add(pack.pick());
        return getHandScore(playerHand) > winScore
                ? getResultOfGame()
                : getMessageOfGameForPlayer();
    }

    public String getResultOfGame() {
        setResult(getHandScore(botHand), "Shaxter");
        setResult(getHandScore(playerHand), "You");
        String answer = String.format("Победители, набравшие 21: %s\n" +
                "Набравшие меньше 21: %s\nПеребравшие: %s", say21, less21, more21);
		if(getHandScore(playerHand) >= getHandScore(playerHand) & getHandScore(playerHand) <= winScore)
            result += 1;
        return answer;
    }

    public void setResult(int sum, String user) {
		if(sum > 21) {
            more21 += user + "-" + sum + " ";
        }
        if (sum < 21) {
            less21 += user + "-" + sum + " ";
        }
        if(sum == 21)
            say21 += user + "-" + sum + " ";
    }

    public Map<Card, Integer> getDictPlay() {
        return new HashMap<>();
    }

    public int getSumPlayer(Map<Card, Integer> hand) {
        return getHandScore(hand.keySet());
    }
    public void setHandPlayer(Map<Card, Integer> hand) {
        playerHand = hand.keySet();
    }

    public void setSumPlayer(int sum) {

    }

    public void setSumBot(int sum) {

    }
}
