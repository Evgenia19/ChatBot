import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestChatBot {

	@Test
	public void Hitest() {
		VicualChatBot test = new VicualChatBot();
		String get_str = "������";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("������������, ��� ��� ������.", output);
	}

	@Test
	public void Qustionstest() {
		VicualChatBot test = new VicualChatBot();
		String get_str = "������";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("������� ������ �� ��������� �����? " + "a) 64 " + "�) 48 " + "�) 36 ", output);
	}

	@Test
	public void Erroranswertest() {
		VicualChatBot test = new VicualChatBot();
		test.sbot.sayInReturn("������", true);
		String get_str = "gfgdg";
		String output = test.sbot.sayInReturn(get_str, true);
		String[] ar_output = output.split("[.]+");
		assertEquals(" ������ ������ �������? " + "�) ������ " + "�) ����� " + "�) ������ ", ar_output[1]);
	}

	@Test
	public void Goodanswertest() {
		VicualChatBot test = new VicualChatBot();
		test.sbot.sayInReturn("������", true);
		String get_str = "64";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("���� ��� ������. " + "������ ������ �������? " + "�) ������ " + "�) ����� " + "�) ������ ",
				output);
	}

	@Test
	public void Continumanswertest() {
		VicualChatBot test = new VicualChatBot();
		test.sbot.sayInReturn("������", true);
		test.sbot.sayInReturn("64", true);
		test.sbot.sayInReturn("�� ���", true);
		String get_str = "����������";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("������ ������ �������? " + "�) ������ " + "�) ����� " + "�) ������ ", output);
	}

}
