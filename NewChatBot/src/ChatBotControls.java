import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatBotControls {	
	
	private static Pattern pattern;
	private static BlackJack black;
	public static Boolean game = false;
	public static String getMassage (String massage) {
		//if(!game) {
			String message = String.join(" ", massage.toLowerCase().split("[ {,|.}?]+"));
			for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) 
			{
				pattern = Pattern.compile(o.getKey());
				if (pattern.matcher(message).find())
				{
					if(o.getValue() == "just") {
						ANSWERS_BY_PATTERNS.put(o.getValue(), addCard());
					}
					if(o.getValue() == "stop")
						ANSWERS_BY_PATTERNS.put(o.getValue(), stop21());
					if(o.getValue() == "new")
						ANSWERS_BY_PATTERNS.put(o.getValue(), new21());
					return ANSWERS_BY_PATTERNS.get(o.getValue());
				}
			}
		//}
		//else {
		//}
		return "Не понимаю о чем вы, лучше спросите кто я";
	}
	
	//final String[] ERROR_ANSWER = { "Увы. ", "Вы ошиблись. ", "Неправильно. ", };
	//final String[] RIGHT_ANSWER = { "Молодец... ", "Правильно... ", "Неплохо вы овседомлены... ", };

	final static Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
		{
			// hello
			put("хай", "hello");
			put("привет", "hello");
			put("здорово", "hello");
			put("здравствуй", "hello");
			// who // name
			put("кто\\s.*ты", "name");
			put("ты\\s.*кто", "name");
			put("как\\s.*зовут", "name");
			put("как\\s.*имя", "name");
			put("есть\\s.*имя", "name");
			put("какое\\s.*имя", "name");
			// start
			put("start", "start");
			put("продолжить", "continum");
			// yes
			put("^да", "yes");
			put("согласен", "yes");
			// bye
			put("прощай", "bye");
			put("пока", "bye");
			put("увидимся", "bye");
			put("до\\s.*свидания", "bye");
			//games
			put("виселица", "gallows");
			put("21", "21");
			put("stop", "stop");
			put("more", "just");
			put("msg", "msg");
			put("new game", "new");
		}
	};

	final static Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "Здравствуйте, рад Вас видеть.");
			put("name",
					"Я обычный чат-бот. Зовите меня Шахттер :). "
					+ "Я очень много знаю о шахматах, и хочу проверить, сколько, Вы, знаете?! "
					+ "Я буду играть с вами в 21. "
					+ "Если хотите начать, то пишите 21. ");
			put("continum", " ");
			put("start", start21());
			put("yes", "Продолжим... ");
			put("bye", "До свидания. Надеюсь, ещё увидимся.");
			//put("gallows", new Gallows());
			put("21", game21());
			//put("just", addCard());
			//put("stop", stop21());
			//put("new", start21());
		}
	};
	
	private static String game21() {
		//game = true;
		return "Правила: " + "\n" + "start - начать игру" + "\n" + "new game - ноавая игра"
				+ "\n" + "more - ещё карту" + "\n" + "stop - хватить";
	}
	
	private static String stop21() {
		//game = false;
		return black.stopCard();
	}
	
	private static String addCard() {
		return black.addCard();
	}
	
	private static String start21( ) {
		black = new BlackJack();
		black.BJ();
		return black.getMessage();
	}
	
	private static String new21() {
		black = null;
		System.gc();
		return start21();
	}
}
