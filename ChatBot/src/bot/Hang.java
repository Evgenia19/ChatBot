package bot;

import java.util.ArrayList;
import java.util.Random;

//TODO Наверное, на логику игры должны быть какие-то тесты?!
public class Hang extends AbstractGame{

    private Random random = new Random();
    private Load loadQuestion;
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
    }

    private void getSymbol(Character smb) {
        correctSymbol = false;
        for (int i = 0; i < word.length(); i++) {
            if (smb == word.charAt(i)) {
                answer[i] = smb;
                symbol = symbol - 1;
                System.out.println(symbol);
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

    private void getWord() {
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

    public String msgHang() {
        if (correctSymbol)
            return "You right" + "\n";
        else
            return "You wrong" + "\n";
    }

    public String playHang(Character smb) {
        getSymbol(smb);
        if(!correct) {
            if(symbol == 0) {
                result += 1;
                return "You win" + "\n" + getText();
            }
            else
                return "lives:" + lives + "\n" + msgHang() + getText();
        }
        else
            return "You wrong" + "\n" + word;
    }

    @Override
    public void start() {
        game += 1;
        loadQuestion = new Load();
        words = loadQuestion.returnWord();
        getWord();
    }

    private void returnCommandsOfGame() {
        setCommands(command);
    }

    public String messageForPlayer() {
        return "lives:" + lives + "\n" + getText();
    }
}
