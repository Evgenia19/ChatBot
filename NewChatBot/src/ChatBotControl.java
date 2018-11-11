package bot;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatBotControl {
	private Pattern pattern;
	private BlackJack black;
	private Hang hang;
	private int result = 0;
	private int game = 0;

	public String game21(String message) {
		if (message.hashCode() == "start".hashCode())
			return start21();
		if (message.hashCode() == "more".hashCode())
			return addCard();
		if (message.hashCode() == "stop".hashCode())
			return stop21();
		if (message.hashCode() == "new game".hashCode())
			return start21();
		if (message.hashCode() == "end".hashCode())
			return EndGame();
		if (message.hashCode() == "help".hashCode())
			return getHelp();
		return "if you want communication then you should write: end";
	}
	
	public String gameHang(String message) {
		if(message.length() == 1)
			return hang.playHang(message.charAt(0));
		if (message.hashCode() == "start".hashCode())
			return startHang();
		if (message.hashCode() == "new game".hashCode())
			return startHang();
		if (message.hashCode() == "end".hashCode())
			return EndGame();
		if (message.hashCode() == "help".hashCode())
			return getHelp();
		return "if you want communication then you should write: end";
	}

	public String getHelp() {
		return "My name Shaxter. I like community and play game."
				+ " I can play 21, hang, quiz. If you want play with me then you should write game";
	}

	public String getMessage(String message) {
		String msg = String.join(" ", message.toLowerCase().split("[ {,|.}?]+"));
		for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
			pattern = Pattern.compile(o.getKey());
			if (pattern.matcher(msg).find())
				return ANSWERS_BY_PATTERNS.get(o.getValue());
		}
		return "I don't understand you, you can write: help";
	}

	final static Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
		{
			// hello
			put("Hi", "hello");
			// who // name
			put("help", "help");
			// start
			put("start", "start");
			// bye
			put("bye", "bye");
			// games
			put("hang", "hang");
			put("21", "21");
		}
	};

	final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "Hi my friend.");
			put("help", "My name Shaxter. I like community and play game. "
					+ "I can play 21, hang, quiz. If you want play with me then you should write game");
			put("bye", "Goodbey.");
			put("21", game21());
			put("hang", hang());
		}
	};

	private String hang() {
		return "Behavire:" + "\n" + "start" + "\n" + "new game" + "end - end game";
	}
	
	private String game21() {
		return "Behavire: " + "\n" + "start" + "\n" + "new game" + "\n" + "more - get card" + "\n"
				+ "stop" + "\n" + "end - end game";
	}

	private String stop21() {
		game += 1;
		String sntns = black.stopCard();
		String[] say = sntns.split(".");
		return sntns;
	}

	private String EndGame() {
		String say = "Result: " + result + " from " + game;
		result = 0;
		game = 0;
		return say;
	}

	private String addCard() {
		return black.addCard();
	}

	private String start21() {
		black = new BlackJack();
		black.blackJack();
		return black.getMessage();
	}
	
	private String startHang() {
		hang = new Hang();
		hang.hang();
		return hang.msgHang();
	}
}