import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestChatBot {

	@Test
	public void Hitest() {
		VicualChatBot test = new VicualChatBot();
		String get_str = "привет";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("Здравствуйте, рад Вас видеть.", output);
	}

	@Test
	public void Qustionstest() {
		VicualChatBot test = new VicualChatBot();
		String get_str = "начать";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("Сколько клеток на шахматной доске? " + "a) 64 " + "б) 48 " + "в) 36 ", output);
	}

	@Test
	public void Erroranswertest() {
		VicualChatBot test = new VicualChatBot();
		test.sbot.sayInReturn("начать", true);
		String get_str = "gfgdg";
		String output = test.sbot.sayInReturn(get_str, true);
		String[] ar_output = output.split("[.]+");
		assertEquals(" Откуда пришли шахматы? " + "а) Египет " + "б) Индия " + "в) Персия ", ar_output[1]);
	}

	@Test
	public void Goodanswertest() {
		VicualChatBot test = new VicualChatBot();
		test.sbot.sayInReturn("начать", true);
		String get_str = "64";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("Пока все просто. " + "Откуда пришли шахматы? " + "а) Египет " + "б) Индия " + "в) Персия ",
				output);
	}

	@Test
	public void Continumanswertest() {
		VicualChatBot test = new VicualChatBot();
		test.sbot.sayInReturn("начать", true);
		test.sbot.sayInReturn("64", true);
		test.sbot.sayInReturn("ты кто", true);
		String get_str = "продолжить";
		String output = test.sbot.sayInReturn(get_str, true);
		assertEquals("Откуда пришли шахматы? " + "а) Египет " + "б) Индия " + "в) Персия ", output);
	}

}
