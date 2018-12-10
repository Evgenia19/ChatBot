package bot;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BlackJackTests {

    private BlackJack blackJack = new BlackJack();
    private Map<Card, Integer> handPlayer;
    private Map<Card, Integer> handBot;
    private Statistic statistic = new Statistic();

    private void setHandPlayer(){
        handPlayer = new HashMap<>();
        handPlayer.put(new Card(Suit.Clubs, Rank.Eight), 8);
        handPlayer.put(new Card(Suit.Hearts, Rank.Seven), 7);
    }

    private void setHandBot(){
        handBot = new HashMap<>();
        handBot.put(new Card(Suit.Diamonds, Rank.Eight), 10);
        handBot.put(new Card(Suit.Diamonds, Rank.Seven), 9);
    }

    @Test
    public void getCardTest() {
        boolean check = true;
        setHandPlayer();
        blackJack.setHandPlayer(handPlayer);
        blackJack.setSumPlayer(15);
        if(blackJack.addCard().equals("You card: Clubs Eight Hearts Seven \n" +
                "result summ: 15"))
            check = false;

        Assert.assertEquals(check, true);
    }

    @Test
    public void getResultTest() {
        blackJack.setSumBot(19);
        blackJack.setSumPlayer(18);
        Assert.assertEquals(blackJack.getResultOfGame(), "Победители, набравшие 21: \n" +
                "Набравшие меньше 21: Shaxter-19 You-18 \n" +
                "Перебравшие: ");
    }

    @Test
    public void getStatistic() {
        blackJack.setSumBot(19);
        blackJack.setSumPlayer(21);
        blackJack.getResultOfGame();
        statistic.getStatistic21(1, blackJack.result);
        Assert.assertEquals(statistic.getStatistic(), "Statistic: \n" +
                "BlackJack: 1 from 1 \n" +
                "Quiz: 0 from 0 \n" +
                "Hang: 0 from 0");
    }

    @Test
    public void getSumOfCardTest() {
        int sum = 0;
        blackJack.start();
        Map<Card, Integer> dict = blackJack.getDictPlay();
        for(Card e: dict.keySet())
            sum += dict.get(e);
        Assert.assertEquals(blackJack.getSumPlayer(dict), sum);
    }

    @Test
    public void getSumTest() {
        setHandPlayer();
        Assert.assertEquals(blackJack.getSumPlayer(handPlayer), 15);
    }

    @Test
    public void getAllCardTest() {

    }

    @Test
    public void getLivesTest() {

    }
}
