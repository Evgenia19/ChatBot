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
        boolean checking = true;
        setHandPlayer();
        blackJack.setPlayerHand(handPlayer);
        blackJack.addCard();
        Set<Card> handPlayerNew = blackJack.getPlayerHand();
        if(new Hand(handPlayerNew).getSum() == new Hand(handPlayer).getSum())
            checking = false;

        Assert.assertEquals(checking, true);
    }

    @Test
    public void getResultTest() {
        setHandPlayer();
        setHandBot();
        boolean flag = false;
        if(new Hand(handPlayer).getSum() < new Hand(handBot).getSum())
            flag = true;
        Assert.assertEquals(flag, true);
    }

    @Test
    public void getStatistic() {
        setHandBot();
        handPlayer = new HashSet<>();
        handPlayer.add(new Card(Suit.Clubs, Rank.Ace));
        handPlayer.add(new Card(Suit.Hearts, Rank.Ten));
        if(new Hand(handPlayer).getSum() > new Hand(handBot).getSum())
            statistic.getStatistic21(1, 1);
        Assert.assertEquals(statistic.getStatistic(), "Statistic: \n" +
                "BlackJack: 1 from 1 \n" +
                "Quiz: 0 from 0 \n" +
                "Hang: 0 from 0");
    }

    @Test
    public void getSumOfCardTest() {
        int sum = 0;
        blackJack.start();
        Set<Card> dict = blackJack.getPlayerHand();
        sum = dict.stream().mapToInt(Card::getScore).sum();
        Assert.assertEquals(new Hand(dict).getSum(), sum);
    }

    @Test
    public void getSumTest() {
        setHandPlayer();
        Assert.assertEquals(new Hand(handPlayer).getSum(), 15);
    }
}
