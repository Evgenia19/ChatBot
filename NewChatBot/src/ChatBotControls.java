import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatBotControls {	
	
	private static Pattern pattern;
	private static BlackJack black;
	public static String gameAnswer(String message) {
		if(message.hashCode() == "start".hashCode())
			return start21();
		if(message.hashCode() == "more".hashCode())
			return addCard();
		if(message.hashCode() == "stop".hashCode())
			return stop21();
		if(message.hashCode() == "new game".hashCode())
			return new21();
		if(message.hashCode() == "end".hashCode())
			return EndGame();
		if(message.hashCode() == "help".hashCode())
			return GetHelp();
		return "�� ����� ����� ������� ��� � ����, ���� ������ ���������� �� ������ end";
	}
	
	public static String GetHelp() {
		return "���� ����� �������. � �������� �������� � 21. ���� ������ ������ ������� ����, �� ������ 21";
	}
	
	public static String getMessage (String message) {
		String msg = String.join(" ", message.toLowerCase().split("[ {,|.}?]+"));
		for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) 
		{
			pattern = Pattern.compile(o.getKey());
			if (pattern.matcher(msg).find())		
				return ANSWERS_BY_PATTERNS.get(o.getValue());
		}
		return "�� ������� � ��� ��... ��������� �������: help";
	}

	final static Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
		{
			// hello
			put("������", "hello");
			// who // name
			put("help", "help");
			// start
			put("start", "start");
			put("����������", "continum");
			// yes
			put("��", "yes");
			// bye
			put("����", "bye");
			//games
			put("����������", "gallows");
			put("21", "21");
		}
	};

	final static Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "������, ��� ��� ����� �������.");
			put("help", "���� ����� �������. � �������� �������� � 21. ���� ������ ������ ������� ����, �� ������ 21");
			put("yes", "�������");
			put("bye", "�� ������ �������.");
			put("21", game21());
		}
	};
	
	private static String game21() {
		return "�������: " + "\n" + "start - ������" + "\n" + "new game - ����� ����"
				+ "\n" + "more - ��� �����" + "\n" + "stop - �������, �����������" + "end - ����� �������� ������";
	}
	
	private static String stop21() {
		return black.stopCard();
	}
	
	private static String EndGame() {
		return "���������";
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