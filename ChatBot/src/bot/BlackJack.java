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

    private void cardFotPlay(Map<Card, Integer> dictPlay) {
        Card card = Card.pickRandom();
        int rankCardForSum = card.rank.ordinal();

        //TODO Опять же не очень понятно, что тут происходит :(
        //TODO Получаем число карты для последующей суммы
        if(!botCard.containsKey(card) & !playerCard.containsKey(card)) {
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
        //TODO Выглядит как баг - почему по два раза методы вызываются с одними аргументами?!
        //Todo Надо две карты для начала игры
        cardFotPlay(botCard);
        cardFotPlay(botCard);
        cardFotPlay(playerCard);
        cardFotPlay(playerCard);
        playGame();

    }

    //TODO из названия(да и в целом) не очень понятно, что этот метод вообще делает
    //TODO рассширяет стандартны словарь игры 
    private void returnCommandsOfGame() {
        command.add(1, "more");
        command.add(2, "stop");
        setCommands(command);
    }

    private void playGame() {
        sumPlayer = getSumCardOnHand(playerCard);
        sumBot = getSumCardOnHand(botCard);
        while(sumBot <= 17) {
            cardFotPlay(botCard);
            sumBot = getSumCardOnHand(botCard);
        }
    }

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

    public String endOfGame() {
        getResult(sumBot, "Shaxter");
        getResult(sumPlayer, "You");
        String answer = String.format("Победители, набравшие 21: %s\n" +
                "Набравшие меньше 21: %s\nПеребравшие: %s", say21, less21, more21);
        if(sumPlayer >= sumBot & sumPlayer <= 21)
            result += 1;
        return answer;
    }

    public void getResult(int summ, String user) {
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
