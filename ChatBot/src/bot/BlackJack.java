package bot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//TODO Наверное, на логику игры должны быть какие-то тесты?!
public class BlackJack extends AbstractGame{

    private Map<Card, Integer> playerCard = new HashMap<Card, Integer>();
    private Map<Card, Integer> botCard = new HashMap<Card, Integer>();
    private Integer sumPlayer = 0;
    private Integer sumBot = 0;
    public int result = 0;
    private ArrayList<String> command;
    String say21 = "";
    String more21 = "";
    String less21 = "";

    BlackJack() {
        command = getCommands();
        returnCommandsOfGame();
    }

	//TODO Неудачное название метода. В методе где-то должен быть глагол
	//TODO Неудачное название параметра(может быть все-таки его hand стоит назвать? :))
	private void cardFotPlay(Map<Card, Integer> dictPlay) {
        Card card = Card.pickRandom();
        int rankCardForSum = card.rank.ordinal();

        if(!botCard.containsKey(card) & !playerCard.containsKey(card)) {
        	//TODO Магические числа в коде. Их стараются делать именованными константами(с понятным именем!), чтобы не гадать, что они значать
            if(rankCardForSum > 5)
                rankCardForSum = rankCardForSum - 4;
            else
                rankCardForSum = rankCardForSum + 6;
            dictPlay.put(card,rankCardForSum);
        }
        else
            cardFotPlay(dictPlay);
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
        cardFotPlay(botCard);
        cardFotPlay(botCard);
        cardFotPlay(playerCard);
        cardFotPlay(playerCard);
        playGame();

    }

    private void returnCommandsOfGame() {
        command.add(1, "more");
        command.add(2, "stop");
        setCommands(command);
    }

    private void playGame() {
        sumPlayer = getSumCardOnHand(playerCard);
        sumBot = getSumCardOnHand(botCard);
		//TODO Магические числа в коде. Их стараются делать именованными константами(с понятным именем!), чтобы не гадать, что они значать
		while(sumBot <= 17) {
            cardFotPlay(botCard);
            sumBot = getSumCardOnHand(botCard);
        }
    }

    //TODO Неудачное название метода. В методе где-то должен быть глагол.
	public String messageForPlayer() {
        StringBuilder card =  new StringBuilder();
        for(Card e : playerCard.keySet())
            card.append(e.strCard() + " ");
        return "You card: " + card + "\n" + "result summ: " + sumPlayer.toString();
    }

    public String addCard() {
        cardFotPlay(playerCard);
        sumPlayer = getSumCardOnHand(playerCard);
        return messageForPlayer();
    }

	//TODO Неудачное название метода. В методе где-то должен быть глагол: end в текущем контексте является существительным.
    public String endOfGame() {
        getResult(sumBot, "Shaxter");
        getResult(sumPlayer, "You");
        String answer = String.format("Победители, набравшие 21: %s\n" +
                "Набравшие меньше 21: %s\nПеребравшие: %s", say21, less21, more21);
		//TODO Магические числа в коде. Их стараются делать именованными константами(с понятным именем!), чтобы не гадать, что они значать
		if(sumPlayer >= sumBot & sumPlayer <= 21)
            result += 1;
        return answer;
    }

    //TODO Неудачное название метода. Когда метод начинается get, то обычно это означает, что он что-то возвращает.
    public void getResult(int summ/*TODO почему summ, а не sum?*/, String user) {
		//TODO Магические числа в коде. Их стараются делать именованными константами(с понятным именем!), чтобы не гадать, что они значать
		if(summ > 21) {
            more21 += user + "-" + summ + " ";
        }
        if (summ < 21) {
            less21 += user + "-" + summ + " ";
        }
        if(summ == 21)
            say21 += user + "-" + summ + " ";
    }
}
