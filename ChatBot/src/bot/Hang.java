package bot;

import java.util.ArrayList;


public class Hang extends AbstractGame{

    private RandomHelpers random = new RandomHelpers();
    private LoadFiles loadQuestion;
    private ArrayList<String> words;
    private Word word;
    private boolean correct = false;
    private boolean correctSymbol = false;
    private int lives = 9;
    private ArrayList<String> command;
    public int result = 0;

    Hang() {
        command = getCommands();
        returnCommandsOfGame();
        loadQuestion = new LoadFiles();
        words = loadQuestion.returnWord();
    }

    private void checkSymbol(Character smb) {
        correctSymbol = word.getCheckedSymbol(smb);
        if (!correctSymbol) {
            if (lives == 1)
                correct = true;
            else
                lives = lives - 1;
        }
    }

    public void choosingWord(ArrayList<String> words) {
        int numb = random.getRandomInt(words.size());
        word = new Word(words.get(numb));
        words.remove(numb);
    }

    @Override
    public void start() {
        choosingWord(words);
    }

    private void returnCommandsOfGame() {
        command.add("start");
        command.add("end");
        command.add("statistic");
        command.add("help");
        setCommands(command);
    }

    public String getText() {
        return word.getHideWord();
    }

    public String getMsgResultHang() {
        if (correctSymbol)
            return "You right" + "\n";
        else
            return "You wrong" + "\n";
    }

    public String playHang(Character smb) {
        checkSymbol(smb);
        if(!correct) {
            if(word.getNewLength() == 0) {
                result += 1;
                return String.format("You win\n%s", getText());
            }
            else
                return String.format("lives: %s\n%s\n%s", lives, getMsgResultHang(), getText());
        }
        else
            return String.format("You wrong\n%s", word.word);
    }

    public String getMessageOfGameForPlayer() {
        return String.format("lives: %s\n%s", lives, getText());
    }
}
