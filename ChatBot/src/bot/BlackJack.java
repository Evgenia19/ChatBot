package bot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class BlackJack extends AbstractGame{

    private Map<Card, Integer> playerCard = new HashMap<Card, Integer>();
    private Map<Card, Integer> botCard = new HashMap<Card, Integer>();
    private Integer sumPlayer = 0;
    private Integer sumBot = 0;
    public int result = 0;
    private ArrayList<String> command;
    private String say21 = "";
    private String more21 = "";
    private String less21 = "";
    private int numberBeforeSmallCount = 5;
    private int differentNumberBetweenCardAndPositionOfArrayForBigCard = 4;
    private int getDifferentNumberBetweenCardAndPositionOfArrayForSmallCard = 6;
    private int sufficientAmount = 17;
    private int winNumber = 21;

    BlackJack() {
        command = getCommands();
        returnCommandsOfGame();
    }
    
	private void getCardForPlay(Map<Card, Integer> dictPlay) {
        Card card = Card.pickRandom();
        int handSum = card.rank.ordinal();

        if(!botCard.containsKey(card) & !playerCard.containsKey(card)) {
        	if(handSum > numberBeforeSmallCount)
                handSum = handSum - differentNumberBetweenCardAndPositionOfArrayForBigCard;
            else
                handSum = handSum + getDifferentNumberBetweenCardAndPositionOfArrayForSmallCard;
            dictPlay.put(card,handSum);
        }
        else
            getCardForPlay(dictPlay);
    }

    private int getSumCardOnHand(Map<Card, Integer> dictPlay) {
        int sumCard = 0;
        for(int e : dictPlay.values()) {
            sumCard += e;
        }
        return sumCard;
    }
    
    @Override
    public void start() {
        getCardForPlay(botCard);
        getCardForPlay(botCard);
        getCardForPlay(playerCard);
        getCardForPlay(playerCard);
        playGame();

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

    private void playGame() {
        sumPlayer = getSumCardOnHand(playerCard);
        sumBot = getSumCardOnHand(botCard);
		while(sumBot <= sufficientAmount) {
            getCardForPlay(botCard);
            sumBot = getSumCardOnHand(botCard);
        }
    }

	public String getMessageOfGameForPlayer() {
        StringBuilder card =  new StringBuilder();
        for(Card e : playerCard.keySet())
            card.append(e.toString() + " ");
        return "You card: " + card + "\n" + "result summ: " + sumPlayer.toString();
    }

    public String addCard() {
        getCardForPlay(playerCard);
        sumPlayer = getSumCardOnHand(playerCard);
        if(sumPlayer > 21)
            return getResultOfGame();
        return getMessageOfGameForPlayer();
    }

    public String getResultOfGame() {
        stringResult(sumBot, "Shaxter");
        stringResult(sumPlayer, "You");
        String answer = String.format("Победители, набравшие 21: %s\n" +
                "Набравшие меньше 21: %s\nПеребравшие: %s", say21, less21, more21);
		if(sumPlayer >= sumBot & sumPlayer <= winNumber)
            result += 1;
        return answer;
    }

    //TODO Не очень удачное название
    public void stringResult(int summ/*TODO почему summ, а не sum?*/, String user) {
		if(summ > 21) {
            more21 += user + "-" + summ + " ";
        }
        if (summ < 21) {
            less21 += user + "-" + summ + " ";
        }
        if(summ == 21)
            say21 += user + "-" + summ + " ";
    }

    public Map<Card, Integer> getDictPlay() {
        return playerCard;
    }
    public int getSumPlayer(Map<Card, Integer> hand) {
        return getSumCardOnHand(hand);
    }
    public void setHandPlayer(Map<Card, Integer> hand) {
        playerCard = hand;
    }

    public void setSumPlayer(int sum) {
        sumPlayer = sum;
    }

    public void setSumBot(int sum) {
        sumBot = sum;
    }
}
