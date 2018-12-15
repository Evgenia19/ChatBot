package bot;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class BlackJackTests {

    private BlackJack blackJack = new BlackJack();
    private Set<Card> handPlayer;
    private Set<Card> handBot;
    private Statistic statistic = new Statistic();

    private void setHandPlayer(){
        handPlayer = new HashSet<>();
        handPlayer.add(new Card(Suit.Clubs, Rank.Eight));
        handPlayer.add(new Card(Suit.Hearts, Rank.Seven));
    }

    private void setHandBot(){
        handBot = new HashSet<>();
        handBot.add(new Card(Suit.Diamonds, Rank.Eight));
        handBot.add(new Card(Suit.Diamonds, Rank.Ten));
    }

    @Test
    public void getCardTest() {
        boolean check = true;
        setHandPlayer();
        blackJack.setPlayerHand(handPlayer);
        blackJack.addCard();
        handPlayer = blackJack.getPlayerHand();
        if(handPlayer.equals("You card: Clubs Eight Hearts Seven \n" +
                "result summ: 15"))
            check = false;

        Assert.assertEquals(check, true);
    }

    @Test
    public void getResultTest() {
        setHandPlayer();
        setHandBot();
        boolean flag = false;
        if(blackJack.returnHandScore(handPlayer) < blackJack.returnHandScore(handBot))
            flag = true;
        Assert.assertEquals(flag, true);
    }

    @Test
    public void getStatistic() {
        //blackJack.setSumBot(19);
        //blackJack.setSumPlayer(21);
        //blackJack.getResultOfGame();
        //statistic.getStatistic21(1, blackJack.result);
        //Assert.assertEquals(statistic.getStatistic(), "Statistic: \n" +
        //        "BlackJack: 1 from 1 \n" +
        //        "Quiz: 0 from 0 \n" +
         //       "Hang: 0 from 0");
    }

    @Test
    public void getSumOfCardTest() {
        int sum = 0;
        blackJack.start();
        Set<Card> dict = blackJack.getPlayerHand();
        sum = dict.stream().mapToInt(Card::getScore).sum();
        Assert.assertEquals(blackJack.returnHandScore(dict), sum);
    }

    @Test
    public void getSumTest() {
        setHandPlayer();
        Assert.assertEquals(blackJack.returnHandScore(handPlayer), 15);
    }
}
