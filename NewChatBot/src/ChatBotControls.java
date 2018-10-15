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
		return "�� ������� � ��� ��, ����� �������� ��� �";
	}
	
	//final String[] ERROR_ANSWER = { "���. ", "�� ��������. ", "�����������. ", };
	//final String[] RIGHT_ANSWER = { "�������... ", "���������... ", "������� �� �����������... ", };

	final static Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
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
			// start
			put("start", "start");
			put("����������", "continum");
			// yes
			put("^��", "yes");
			put("��������", "yes");
			// bye
			put("������", "bye");
			put("����", "bye");
			put("��������", "bye");
			put("��\\s.*��������", "bye");
			//games
			put("��������", "gallows");
			put("21", "21");
			put("stop", "stop");
			put("more", "just");
			put("msg", "msg");
			put("new game", "new");
		}
	};

	final static Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "������������, ��� ��� ������.");
			put("name",
					"� ������� ���-���. ������ ���� ������� :). "
					+ "� ����� ����� ���� � ��������, � ���� ���������, �������, ��, ������?! "
					+ "� ���� ������ � ���� � 21. "
					+ "���� ������ ������, �� ������ 21. ");
			put("continum", " ");
			put("start", start21());
			put("yes", "���������... ");
			put("bye", "�� ��������. �������, ��� ��������.");
			//put("gallows", new Gallows());
			put("21", game21());
			//put("just", addCard());
			//put("stop", stop21());
			//put("new", start21());
		}
	};
	
	private static String game21() {
		//game = true;
		return "�������: " + "\n" + "start - ������ ����" + "\n" + "new game - ������ ����"
				+ "\n" + "more - ��� �����" + "\n" + "stop - �������";
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
