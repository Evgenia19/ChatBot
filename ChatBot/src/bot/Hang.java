package bot;

import java.util.ArrayList;
import java.util.Random;

public class Hang extends AbstractGame{

    private Random random = new Random();
    private LoadFile loadQuestion;
    private ArrayList<String> words;
    private String word;
    private char[] answer;
    private boolean correct = false;
    private boolean correctSymbol = false;
    private int lives = 9;
    private int symbol;
    private ArrayList<String> command;
    public int result = 0;
    public int game = 0;

    Hang() {
        command = getCommands();
        returnCommandsOfGame();
        loadQuestion = new LoadFile();
        words = loadQuestion.returnWord();
    }

    private void handlingSymbol(Character smb) {
        correctSymbol = false;
        for (int i = 0; i < word.length(); i++) {
            if (smb == word.charAt(i)) {
                answer[i] = smb;
                symbol = symbol - 1;
                correctSymbol = true;
            }
        }
        if(!correctSymbol) {
            if (lives == 1)
                correct = true;
            else
                lives = lives -  1;
        }
    }

    private void handlingWord(ArrayList<String> words) {
        int numb = random.nextInt(words.size());
        word = words.get(numb);
        words.remove(numb);
        symbol = word.length();
        answer = new char[symbol];
        for (int i = 0; i < symbol; i++) {
            answer[i] = '_';
        }
    }

    public String getText() {
        String text = "";
        for (int i = 0; i < answer.length; i++) {
            text += answer[i] + " ";
        }
        return text;
    }

    public String getMsgResultHang() {
        if (correctSymbol)
            return "You right" + "\n";
        else
            return "You wrong" + "\n";
    }

    public String playHang(Character smb) {
        handlingSymbol(smb);
        if(!correct) {
            if(symbol == 0) {
                result += 1;
                return "You win" + "\n" + getText();
            }
            else
                return "lives:" + lives + "\n" + getMsgResultHang() + getText();
        }
        else
            return "You wrong" + "\n" + word;
    }

    @Override
    public void start() {
        game += 1;
        handlingWord(words);
    }

    private void returnCommandsOfGame() {
        command.add("start");
        command.add("end");
        command.add("statistic");
        command.add("help");
        setCommands(command);
    }

    @Override
    public String getMessageOfGameForPlayer() {
        return "lives:" + lives + "\n" + getText();
    }
}
