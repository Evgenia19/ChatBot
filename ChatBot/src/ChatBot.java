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
			put("���", "hello");
			put("������", "hello");
			put("�������", "hello");
			put("����������", "hello");
			// who // name
			put("���\\s.*��", "name");
			put("��\\s.*���", "name");
			put("���\\s.*�����", "name");
			put("���\\s.*���", "name");
			put("����\\s.*���", "name");
			put("�����\\s.*���", "name");
			put("help", "name");
			// start
			put("start", "start");
			// contin
			put("����������", "continum");
			// yes
			put("^��", "yes");
			put("��������", "yes");
			// bye
			put("������", "bye");
			put("��������", "bye");
			put("��\\s.*��������", "bye");
			//chess
			put("�������", "chess");
		}
	};
	
	static final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "������������, ��� ��� ������.");
			put("name",
					"� ������� ���-���. ������ ���� ������� :). � ����� ����� ���� � ��������, � ���� ���������, �������, ��, ������?! � ���� �������� �������, � ��������� ���� ������. ���� ������ ������, �� ������ ������. � ���� ����������, �� �����������.");
			put("continum", " ");
			put("start", "�������� ����: " + "�������  " + "������");
			put("yes", "���������... ");
			put("bye", "�� ��������. �������, ��� ��������.");
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
			String say = "�� ������� � ��� ��. ���������� �������� ������� help";
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
