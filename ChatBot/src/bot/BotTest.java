package bot;
import org.junit.Assert;
import org.junit.Test;

public class BotTest {
	private String userId_1 = "one";
	private String userId_2 = "two";
	private BlackJack bj;
	
	@Test
	public void testCreate() {
		new ChatBot("one");
	}

	@Test
	public void testStarts() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "������"));
		Assert.assertEquals("������, ��� ��� ����� �������.", msg.content);
	}

	@Test
	public void testHelps() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "help"));
		Assert.assertEquals(
				"���� ����� �������. � �������� �������� � 21. ���� ������ ������ ������� ����, �� ������ 21",
				msg.content);
	}

	@Test
	public void testOnNotUderstand() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "asas"));
		Assert.assertEquals("�� ������� � ��� ��... ��������� �������: help", msg.content);
	}

	@Test
	public void testGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "21"));
		Assert.assertEquals("�������: " + "\n" + "start - ������" + "\n" + "new game - ����� ����"
				+ "\n" + "more - ��� �����" + "\n" + "stop - �������, �����������" + "\n" + "end - ����� �������� ������", msg.content);
	}
	
	@Test
	public void testStartGame() {
		bj = new BlackJack();
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "start"));
		Assert.assertEquals(bj.getMessage(), msg.content);
	}

	@Test
	public void testOnNotUderstandInGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "start"));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "asas"));
		Assert.assertEquals("�� ����� ����� ������� ��� � ����, ���� ������ ���������� �� ������ end", msg.content);
	}
	
	@Test
	public void testNewGame() {
		bj = new BlackJack();
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "start"));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "new game"));
		Assert.assertEquals(bj.getMessage(), msg.content);
	}

	@Test
	public void testHelpsWhileGame() {
		bj = new BlackJack();
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21"));
		bot_1.process(new UserMessage(userId_1, "start"));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "help"));
		Assert.assertEquals(
				"���� ����� �������. � �������� �������� � 21. ���� ������ ������ ������� ����, �� ������ 21",
				msg.content);
	}
	
	@Test
	public void testStopGame_1() {
		ChatBot bot_2 = new ChatBot(userId_1);
		bot_2.process(new UserMessage(userId_1, "21"));
		bot_2.process(new UserMessage(userId_1, "start"));
		bot_2.process(new UserMessage(userId_1, "more"));
		bot_2.process(new UserMessage(userId_1, "stop"));
		UserMessage msg = bot_2.process(new UserMessage(userId_1, "end"));
		Assert.assertEquals("���������: 0 �� 1", msg.content);
	}
	
	@Test
	public void testAddCard() {
		bj = new BlackJack();
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21"));
		bot_1.process(new UserMessage(userId_1, "start"));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "more"));
		Assert.assertEquals(bj.getMessage(), msg.content);
	}

	@Test
	public void testEndGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "start"));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "end"));
		Assert.assertEquals("���������: 0 �� 0", msg.content);
	}
	
	@Test
	public void testEndGameAndCommunit() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "start"));
		bot_1.process(new UserMessage(userId_1, "end"));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "����"));
		Assert.assertEquals("�� ������ �������.", msg.content);
	}
	
	@Test
	public void testWorkTwoBots() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "������"));
		ChatBot bot_2 = new ChatBot(userId_2);
		UserMessage msg_2 = bot_2.process(new UserMessage(userId_2, "������"));
		UserMessage msg_1 = bot_1.process(new UserMessage(userId_1, "help"));
		Assert.assertEquals(
				"���� ����� �������. � �������� �������� � 21. ���� ������ ������ ������� ����, �� ������ 21",
				msg_1.content);
		Assert.assertEquals("������, ��� ��� ����� �������.", msg_2.content);
	}
}