package bot;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class HangTest {

    private Hang hang = new Hang();
    private ArrayList<String> words = new ArrayList<>();

    @Test
    public void getLivesTest() {
        words.add("сходимость");
        hang.handlingWord(words);
        hang.playHang('а');
        Assert.assertEquals(hang.getMessageOfGameForPlayer(), "lives:8\n_ _ _ _ _ _ _ _ _ _ ");
    }

    @Test
    public void getWordTest() {
        words.add("сходимость");
        hang.handlingWord(words);
        Assert.assertEquals(hang.getMessageOfGameForPlayer(),"lives:9\n_ _ _ _ _ _ _ _ _ _ ");
    }

    @Test
    public void livesTest() {
        words.add("сходимость");
        hang.handlingWord(words);
        hang.playHang('а');
        hang.playHang('о');
        hang.playHang('п');
        hang.playHang('и');
        Assert.assertEquals(hang.getMessageOfGameForPlayer(), "lives:7\n_ _ о _ и _ о _ _ _ ");
    }

    @Test
    public void getWinTest() {
        words.add("папа");
        hang.handlingWord(words);
        hang.playHang('а');
        hang.playHang('о');
        hang.playHang('и');
        //hang.playHang('п');
        //Assert.assertEquals(hang.getMessageOfGameForPlayer(), "lives:7\nп а п а");
        Assert.assertEquals(hang.playHang('п'), "You win\nп а п а ");
    }

    @Test
    public void getWrongTest() {
        words.add("сходимость");
        hang.handlingWord(words);
        hang.playHang('а');
        hang.playHang('е');
        hang.playHang('и');
        hang.playHang('ы');
        hang.playHang('ф');
        hang.playHang('ц');
        hang.playHang('ч');
        hang.playHang('р');
        hang.playHang('г');
        Assert.assertEquals(hang.playHang('п'), "You wrong\nсходимость");
    }

    @Test
    public void getMessageTest() {

    }

    @Test
    public void loadWordTest() {

    }
}
