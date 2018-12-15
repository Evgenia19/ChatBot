package bot;

import java.util.ArrayList;
import java.util.Random;

public class Hang extends AbstractGame{

    private Random random = new Random();
    private LoadFiles loadQuestion;
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
        loadQuestion = new LoadFiles();
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

    public void choosingWord(ArrayList<String> words) {
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
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            text.append(answer[i] + " ");
        }

        return text.toString();
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
        choosingWord(words);
    }

    private void returnCommandsOfGame() {
        command.add("start");
        command.add("end");
        command.add("statistic");
        command.add("help");
        setCommands(command);
    }

    public String getMessageOfGameForPlayer() {
        return "lives:" + lives + "\n" + getText();
    }
}
