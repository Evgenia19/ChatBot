package bot;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//TODO Наверное, на логику игры должны быть какие-то тесты?!
public class BlackJack extends Game{

	private Random random = new Random();
	//TODO Не очень понятно, что означает это поле
	private Map<Card, Integer> listPlayer = new HashMap<Card, Integer>();
	//TODO Не очень понятно, что означает это поле
	private Map<Card, Integer> listBot = new HashMap<Card, Integer>();
	private Integer sumPlayer = 0;
	private Integer sumBot = 0;
	public int result = 0;
	//TODO Не очень понятно, что означает это поле
	public int game= 0;
	private String[] command;
	
	BlackJack() {
		command = getCommand();
		returnCommand();
	}

	//TODO метод очень плохо назван. Используется для получения и мастей, и номиналов, однако, называется как-то про масти.
    private int randomSuit(int numb) {
		return random.nextInt(numb);
	}

	//TODO метод очень плохо назван. Вроде бы по названию должен что-то возвращать, однако, он void
	private void getMast(Map<Card, Integer> dictPlay) {
		//TODO Перепишите, пожалуйста, код(BEGIN-END), используя Card.pickRandom()
		// BEGIN
		Suit[] suitCard = Suit.values();
		Rank[] numberCard = Rank.values();
		int num = randomSuit(4);
		//TODO переменную нужно называть существительным, getSuit немного не оно
		Suit getSuit = suitCard[num];
		num = randomSuit(13);
		//TODO переменную нужно называть существительным, getNumber немного не оно
		Rank getNumber = numberCard[num];
		Card card = new Card(getSuit, getNumber);
		// END

		//TODO Опять же не очень понятно, что тут происходит :(
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

	//TODO Не очень понятно названия метода + параметр непонятно назван
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
		//TODO Выглядит как баг - почему по два раза методы вызываются с одними аргументами?!
		getMast(listBot);
		getMast(listBot);
		getMast(listPlayer);
		getMast(listPlayer);
		playGame();
		
	}

	//TODO из названия(да и в целом) не очень понятно, что этот метод вообще делает
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

	//TODO Не очень понятное название метода
	public String getMsg() {
		//TODO Можно сделать метод toString у Card и у нести часть логики туда
		//TODO Конечно, хотелось бы, чтобы вы использовали StringBuilder
		String card = "";
		for(Card e : listPlayer.keySet())
			card += e.suit.toString() + " " + e.rank.toString() + ",  ";
		return "You card: " + card + "\n" + "result summ: " + sumPlayer.toString();
	}
	
	public String addCard() {
		getMast(listPlayer);
		sumPlayer = getSum(listPlayer);
		return getMsg();
	}

	//TODO Не очень понятное название метода
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
