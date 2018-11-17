import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;


public class ChatBot {
	
	public static Map<String,String> variatyQuestions = new HashMap<String,String>();
	
	public static void getDict( ) {
		variatyQuestions.put("Chess", "questions.txt");
	}

	
	public static String Start(String text) {
		String document = variatyQuestions.get(text);
		String say = GetQuestion.loadQuestions(document);
		return say;
	}
	
	static final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
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
			put("help", "name");
			// start
			put("start", "start");
			// contin
			put("продолжить", "continum");
			// yes
			put("^да", "yes");
			put("согласен", "yes");
			// bye
			put("прощай", "bye");
			put("увидимся", "bye");
			put("до\\s.*свидания", "bye");
			//chess
			put("шахматы", "chess");
		}
	};
	
	static final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "Здравствуйте, рад Вас видеть.");
			put("name",
					"Я обычный чат-бот. Зовите меня Шахттер :). Я очень много знаю о шахматах, и хочу проверить, сколько, Вы, знаете?! Я буду задавать вопросы, и оценивать ваши ответы. Если хотите начать, то пишите начать. А если продолжить, то догадайтесь.");
			put("continum", " ");
			put("start", "Выбирите тему: " + "Шахматы  " + "Музыка");
			put("yes", "Продолжим... ");
			put("bye", "До свидания. Надеюсь, ещё увидимся.");
			//put("chess", Start(variatyQuestions.get("Chess")));
			//put("music", Start(variatyQuestions.get("Music")));
		}
	};
	
	static Pattern pattern;
	Random random;

	public ChatBot() {
		//String userId = id;
		random = new Random();
	}
	
	static Boolean flag = false;
	public static String sayInReturn(String msg) {
		if (!flag) {
			String say = "Не понимаю о чем вы. Попробуйте попросиь справку help";
			String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
			for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
				pattern = Pattern.compile(o.getKey());
				if (pattern.matcher(message).find())
					return ANSWERS_BY_PATTERNS.get(o.getValue());
			}
		}
		else {
			
		}
			
		return say;
	}
}
