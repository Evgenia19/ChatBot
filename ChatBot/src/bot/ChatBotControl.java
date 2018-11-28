package bot;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatBotControl {
	private Game game;
	private Pattern pattern;
	private BlackJack blackJack;
	private Hang hang;
	private Quiz quiz;

	public String game(String message, Map<ChatBotState, String[]> commands, ChatBotState state) {
		if (message.length() == 1)
			switch(state) {
			case Hang:
				return hang.playHang(message.charAt(0));
			case Quiz:
				return quiz.askQuestion(message);
			default:
				break;
			}
		String[] command = commands.get(state);
		for(int i = 0; i < command.length; i++) {
			if (command[i].equals(message)) {
				switch(message) {
				case "help":
					hang = new Hang();
					return hang.getHelp();
				case "more":
					return addCard();
				case "stop":
					return stop21();
				case "start":
					return start(state);
				case "new game":
					return start(state);
				case "end game":
					return hang.end();
				}
			}
		}
		return "if you want communication then you should write: end";	
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

	final  Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
		{
			// hello
			put("Hi", "hello");
			// who // name
			put("help", "help");
			// start
			put("start", "start");
			// bye
			put("bye", "bye");
		}
	};

	final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "Hi my friend.");
			put("help", "My name Shaxter. I like community and play game. "
					+ "I can play 21, hang, quiz. If you want play with me then you should write game");
			put("bye", "Goodbey.");
		}
	};

	private String stop21() {
		String sntns = blackJack.stopCard();
		return sntns;
	}

	private String addCard() {
		return blackJack.addCard();
	}
	
	private String start(ChatBotState state) {
		switch(state) {
		case Hang:
			return startHang();
		case Quiz:
			return startQuiz();
		case BlackJack:
			return start21();
		default:
			return null;
		}
	}

	private String start21() {
		blackJack = new BlackJack();
		blackJack.start();
		return blackJack.getMsg();
	}
	
	private String startHang() {
		hang = new Hang();
		hang.start();
		return hang.getMsg();
	}
	
	private String startQuiz() {
		quiz = new Quiz();
		quiz.start();
		return quiz.getMsg();
	}
}
