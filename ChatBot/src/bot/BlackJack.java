package bot;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlackJack extends Game{

	private Random random = new Random();
	private Map<Card, Integer> listPlayer = new HashMap<Card, Integer>();
	private Map<Card, Integer> listBot = new HashMap<Card, Integer>();
	private Integer sumPlayer = 0;
	private Integer sumBot = 0;
	public int result = 0;
	public int game= 0;
	private String[] command;
	
	BlackJack() {
		command = getCommand();
		returnCommand();
	}
    
    private int randomSuit(int numb) {
		return random.nextInt(numb);
	}
	
	private void getMast(Map<Card, Integer> dictPlay) {
		SuitCard[] suitCard = SuitCard.values();
		NumberCard[] numberCard = NumberCard.values();
		int num = randomSuit(4);
		SuitCard getSuit = suitCard[num];
		num = randomSuit(13);
		NumberCard getNumber = numberCard[num];
		Card card = new Card(getSuit, getNumber);
		if(!listBot.containsKey(card) & !listPlayer.containsKey(card)) {
			if(num > 9)
				num = num - 8;
			else
				num = num + 2;
			dictPlay.put(card, num);
		}
		else
			getMast(dictPlay);
	}
	
	private int getSum(Map<Card, Integer> dictPlay) {
		int sum = 0;
		for(int e : dictPlay.values()) {
			sum += e;
		}
		return sum;
	}
	@Override
	public void start() {
		game += 1;
		getMast(listBot);
		getMast(listBot);
		getMast(listPlayer);
		getMast(listPlayer);
		playGame();
		
	}
	
	private void returnCommand() {
		int i = command.length;
		String[] temp = new String[i + 2];
		for (int j = 0; j < i; j++)
			temp[j] = command[j];
		temp[i] = "stop";
		temp[i+1] = "more";
		command = temp;
		setCommand(command);
	}
	
	private void playGame() {
		sumPlayer = getSum(listPlayer);
		sumBot = getSum(listBot);
		while(sumBot <= 17) {
			getMast(listBot);
			sumBot = getSum(listBot);
		}
	}

	public String getMsg() {
		String card = "";
		for(Card e : listPlayer.keySet())
			card += e.suit.toString() + " " + e.number.toString() + ",  ";
		return "You card: " + card + "\n" + "result summ: " + sumPlayer.toString();
	}
	
	public String addCard() {
		getMast(listPlayer);
		sumPlayer = getSum(listPlayer);
		return getMsg();
	}
	
	public String stopCard() {
		String say = "I have " + sumBot.toString();
		if(sumPlayer > 21 && sumBot > 21)
				say += "d";
		else {
			if(sumPlayer > sumBot) {
				if (sumPlayer > 21 && sumBot <= 21)
					say += "... I win";
				if (sumPlayer <= 21) {
					say += "... You win";
					result += 1;
				}
			}
			if(sumPlayer == sumBot) {
				if(listPlayer.size() > listBot.size()) {
					say += "... You win";
					result += 1;
				}
				if(listPlayer.size() == listBot.size()) {
					say += "... equal";
					result += 0.5;
				}
				if(listPlayer.size() < listBot.size() )
					say += "... I win";
			}
			if(sumBot > sumPlayer) {
				if (sumPlayer <= 21 && sumBot > 21) {
					say += "...  You win";
					result += 1;
				}
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
