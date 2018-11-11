package bot;
import java.util.Random;

public class Hang {
	private Random random = new Random();
	private String word;
	private Character[] answer;
	private Boolean correct = false;
	private String[] words = {"Поступок", "Экземпляр", "Чужестранец", "Притяжение"};
	private int lives = 9;

	public void hang() {
		getWord();
	}

	private void getSymbol(Character symbol) {
		for (int i = 0; i < word.length(); i++) {
			if (symbol == word.charAt(i)) {
				answer[i - 1] = symbol;
			} else {
				if (lives == 1)
					correct = true;
				else
					lives -= 1;
			}
		}
	}

	private void getWord() {
		int numb = random.nextInt(words.length);
		word = words[numb];
		for (int i = 0; i < word.length() - 1; i++) {
			answer[i] = '_';
		}
	}

	public String msgHang() {
		if (!correct) {
			String text = "";
			for (int i = 0; i < answer.length; i++) {
				text += answer[i];
			}
			return text;
		}
		else
			return "You wrong";
	}
	
	public String playHang(Character symbol) {
		getSymbol(symbol);
		return msgHang();
	}

}
