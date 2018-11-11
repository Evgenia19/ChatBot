package bot;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlackJack {

	private Random random = new Random();
	private Map<String, Integer> listPlayer = new HashMap<String, Integer>();
	private Map<String, Integer> listBot = new HashMap<String, Integer>();
	private String[] mast = {"Buben", "Chervi", "Piki", "Cresti"};
	private String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "T"};
	private Integer sumPlayer = 0;
	private Integer sumBot = 0;
	
	private int randomMast(int numb) {
		return random.nextInt(numb);
	}
	private void getMast(Map<String, Integer> dictPlay) {
		int num = randomMast(4);
		String getMast = mast[num];
		int numb = randomMast(13);
		String getNumber = number[numb];
		String card = getNumber + " " + getMast;
		if(!listBot.containsKey(card) & !listPlayer.containsKey(card)) {
			if(numb > 9)
				numb = numb - 8;
			else
				numb = numb + 2;
			dictPlay.put(card, numb);
		}
		else
			getMast(dictPlay);
	}
	
	private int getSum(Map<String, Integer> dictPlay) {
		int sum = 0;
		for(int e : dictPlay.values()) {
			sum += e;
		}
		return sum;
	}
	
	public void blackJack() {
		getMast(listBot);
		getMast(listBot);
		getMast(listPlayer);
		getMast(listPlayer);
		playGame();
		
	}
	
	private void playGame() {
		sumPlayer = getSum(listPlayer);
		sumBot = getSum(listBot);
		while(sumBot <= 17) {
			getMast(listBot);
			sumBot = getSum(listBot);
		}
	}
	
	public String getMessage() {
		String card = "";
		for(String e : listPlayer.keySet())
			card += e + ",  ";
		return "You card: " + card + "\n" + "result summ: " + sumPlayer.toString();
	}
	
	public String addCard() {
		getMast(listPlayer);
		sumPlayer = getSum(listPlayer);
		return getMessage();
	}
	
	public String stopCard() {
		String say = "I have " + sumBot.toString();
		if(sumPlayer > 21 && sumBot > 21)
				say += "d";
		else {
			if(sumPlayer > sumBot) {
				if (sumPlayer > 21 && sumBot <= 21)
					say += "... I win";
				if (sumPlayer <= 21)
					say += "... You win";
			}
			if(sumPlayer == sumBot) {
				if(listPlayer.size() > listBot.size())
					say += "... You win";
				if(listPlayer.size() == listBot.size())
					say += "... equal";
				if(listPlayer.size() < listBot.size() )
					say += "... I win";
			}
			if(sumBot > sumPlayer) {
				if (sumPlayer <= 21 && sumBot > 21)
					say += "...  You win";
				if (sumBot <= 21)
					say += "... I win";
			}
		}
		listBot.clear();
		listPlayer.clear();
		sumPlayer = 0;
		sumBot = 0;
		return say;
	}
}