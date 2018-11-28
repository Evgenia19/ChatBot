package bot;

import java.util.ArrayList;
import java.util.Random;

public class Hang extends Game{

	private Random random = new Random();
	private Load loadQuestion;
	private ArrayList<String> words;
	private String word;
	private Character[] answer;
	private Boolean correct = false;
	private Boolean correctSymbol = false;
	private int lives = 9;
	private int symbol;
	public int result = 0;
	public int game = 0;

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
		answer = new Character[symbol];
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
			return "You wrong";
	}
	
	@Override
	public void start() {
		game += 1;
		loadQuestion = new Load();
		words = loadQuestion.returnWord();
		getWord();
	}

	public String getMsg() {
		return "lives:" + lives + "\n" + getText();
	}
}
