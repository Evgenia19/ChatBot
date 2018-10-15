import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlackJack {

	private static Random random = new Random();
	private static Map<String, Integer> listPlayer = new HashMap<String, Integer>();
	private static Map<String, Integer> listBot = new HashMap<String, Integer>();
	private static String[] mast = {"Червей", "Пикей", "Крестей", "Бубей"};
	private static String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Т", "В", "Д", "К"};
	private static Integer sumPlayer = 0;
	private static Integer sumBot = 0;
	
	private static int randomMast(int numb) {
		return random.nextInt(numb);
	}
	private static void getMast(Map<String, Integer> dictPlay) {
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
	
	private static int getSum(Map<String, Integer> dictPlay) {
		int sum = 0;
		for(int e : dictPlay.values()) {
			sum += e;
		}
		return sum;
	}
	
	public void BJ() {
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
		return "У вас карты: " + card + "\n" + "Ваши очки: " + sumPlayer.toString();
	}
	
	public String addCard() {
		getMast(listPlayer);
		sumPlayer = getSum(listPlayer);
		return getMessage();
	}
	
	public String stopCard() {
		String say = "I have " + sumBot.toString();
		if(sumPlayer > 21 && sumBot > 21)
				say += "... Никто не выиграл...";
		else {
			if(sumPlayer > sumBot) {
				if (sumPlayer > 21 && sumBot <= 21)
					say += "... I win";
				if (sumPlayer <= 21)
					say += " You win";
			}
			if(sumPlayer == sumBot) {
				if(listPlayer.size() > listBot.size())
					say += "... You win";
				if(listPlayer.size() == listBot.size())
					say += "... Ничья";
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