package bot;

import java.util.*;
import java.util.regex.*;

public class SimpleBot {

	static final String[] QUSTIONS = { "������� ������ �� ��������� �����? " + "a) 64 " + "�) 48 " + "�) 36 ",
			"������ ������ �������? " + "�) ������ " + "�) ����� " + "�) ������ ",
			"����� ������ ����� ������ �� �����? " + "�) ������ " + "�) ����� " + "�) ����� ",
			"����� ����� ����� ��������� ������ �� �������? " + "�) ����� " + "�) ����� " + "�) ����� ",
			"����� ������ ����� ��� ���� � �����? " + "�) ������ " + "�) ������� " + "�) ����� ",
			"������ ������� ���� �� ��������? " + "�) �������� " + "�) ���� " + "�) ������� ",
			"��� ���������������� �������? " + "�) ��� � ��� " + "�) ������ ������ " + "�) ����������� ���� ",
			"����������� ������� ����? " + "�) ����� " + "�) ������� " + "�) ������� ",
			"�������� ������� ���������� ����������� ����? " + "�) �������� " + "�) �������� " + "�) ��������� ",
			"��� ���������� ����� ������ ������: �4 Kf6, �5 Kd5? " + "�) ������ ������� " + "�) ������ 2� ����� "
					+ "�) ��������� ������ ", };

	static final String[] ERROR_ANSWERS = { "���. ", "�� ��������. ", "�����������. ", };

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
			// 64
			put("64", "64");
			put("����������\\\\s.*������", "64");
			// India
			put("�����", "India");
			// King
			put("������", "King");
			// peshka
			put("�����", "peshka");
			// Queen
			put("�����", "Queen");
			// Steanist
			put("�������", "Stenist");
			// died king
			put("������\\s.*������", "dead king");
			// Karleson
			put("�������", "Karleson");
			// Iluminginov
			put("������\\s.*���������", "Iluminginov");
			// Alexina
			put("������\\s.*�������", "Alexina");
			// start
			put("������", "start");
			// contin
			put("����������", "continum");
			// yes
			put("^��", "yes");
			put("��������", "yes");
			// bye
			put("������", "bye");
			put("��������", "bye");
			put("��\\s.*��������", "bye");
		}
	};

	static final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		{
			put("hello", "������������, ��� ��� ������.");
			put("name",
					"� ������� ���-���. ������ ���� ������� :). � ����� ����� ���� � ��������, � ���� ���������, �������, ��, ������?! � ���� �������� �������, � ��������� ���� ������. ���� ������ ������, �� ������ ������. � ���� ����������, �� �����������.");
			put("continum", " ");
			put("start", QUSTIONS[0]);
			put("Alexina", "�� �������! ");
			put("Iluminginov", "��� ��� ������� ������. ");
			put("Karleson", "��������� ����. ");
			put("dead king", "������� �����. ");
			put("Stenist", "�� �����! ");
			put("Queen", "������ �� ������. ");
			put("peshka", "��� �� ��������. ");
			put("King", "��� �� ����� ������. ");
			put("64", "���� ��� ������. ");
			put("India", "������ ��������. ");
			put("yes", "���������... ");
			put("bye", "�� ��������. �������, ��� ��������.");
		}
	};
	Pattern pattern; // for regexp
	Random random; // for random answers

	public SimpleBot() {
		random = new Random();
	}

	int count = 0;
	static int i = 0;

	public String sayInReturn(String msg, boolean ai) {
		String say = "�� ������� � ��� ��, ���� ������ ������ ���������� ��� ������";
		if (i >= QUSTIONS.length) {
			say = "��������� ����������. ��� ���������: " + Integer.toString(count) + "����������. "
					+ "������ ������� ��� ���?";
			i = 0;
		}
		if (ai) {
			String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
			for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
				pattern = Pattern.compile(o.getKey());
				if (pattern.matcher(message).find()) {
					if (o.getValue().equals("continum"))
						return QUSTIONS[i];
					if (o.getValue().equals("hello") || o.getValue().equals("name") || o.getValue().equals("buy")
							|| o.getValue().equals("start"))
						return ANSWERS_BY_PATTERNS.get(o.getValue());
					else {
						count += 1;
						say = ANSWERS_BY_PATTERNS.get(o.getValue());
						i = i + 1;
						if (i > 0)
							return say + QUSTIONS[i];
					}
				} else
					say = ERROR_ANSWERS[random.nextInt(ERROR_ANSWERS.length)] + QUSTIONS[i];
			}
		}
		return say;
	}
}