package bot;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class BotTest {
	private String userId_1 = "one";
	private String userId_2 = "two";
	private BlackJack bj = new BlackJack();
	private Quiz quiz = new Quiz();
	private Hang hang = new Hang();
	private ChatBotControl control = new ChatBotControl();
	private Moving move = new Moving();
	private Map<ChatBotState, String[]> commands = move.returnMove();
	private String[] games = new String[] {"quiz", "hang", "21"};
	
	@Test
	public void testCreate() {
		new ChatBot("one");
	}

	@Test
	public void testStarts() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "hi", null));
		Assert.assertEquals("Hi my friend.", msg.content);
	}

	@Test
	public void testHelps() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "help", null));
		Assert.assertEquals(
				"My name Shaxter. I like community and play game."
						+ " I can play 21, hang, quiz. If you want play with me then you should write game",
				msg.content);
	}

	@Test
	public void testOnNotUderstand() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "asas", null));
		Assert.assertEquals("I don't understand you, you can write: help", msg.content);
	}

	@Test
	public void testGame21() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "21", null));
		Assert.assertEquals(bj.getBehavior(), msg.content);
	}
	
	@Test
	public void testGameQuiz() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "quiz", null));
		Assert.assertEquals(quiz.getBehavior(), msg.content);
	}
	
	@Test
	public void testGameHang() {
		ChatBot bot_1 = new ChatBot(userId_1);
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "hang", null));
		Assert.assertEquals(hang.getBehavior(), msg.content);
	}
	
	@Test
	public void testStartGame21() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21", null));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "start", null));
		Assert.assertEquals(bj.getMsg(), msg.content);
	}
	
	@Test
	public void testOnNotUderstandInGame() {
		for(String e : games) {
			ChatBot bot_1 = new ChatBot(userId_1);
			bot_1.process(new UserMessage(userId_1, e, null));
			UserMessage msg = bot_1.process(new UserMessage(userId_1, "asas", null));
			Assert.assertEquals("if you want communication then you should write: end", msg.content);
		}
	}
	
	@Test
	public void testNewGame21() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21", null));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "new game", null));
		Assert.assertEquals(bj.getMsg(), msg.content);
	}

	@Test
	public void testHelpsWhileGame() {
		for(String e : games) {
			ChatBot bot_1 = new ChatBot(userId_1);
			bot_1.process(new UserMessage(userId_1, e, null));
			bot_1.process(new UserMessage(userId_1, "start", null));
			UserMessage msg = bot_1.process(new UserMessage(userId_1, "help", null));
			Assert.assertEquals(bj.getHelp(), msg.content);
		}
	}
	
	@Test
	public void testStopGame_1() {
		ChatBot bot_2 = new ChatBot(userId_1);
		bot_2.process(new UserMessage(userId_1, "21", null));
		bot_2.process(new UserMessage(userId_1, "start", null));
		bot_2.process(new UserMessage(userId_1, "more", null));
		bot_2.process(new UserMessage(userId_1, "stop", null));
		UserMessage msg = bot_2.process(new UserMessage(userId_1, "end", null));
		Assert.assertEquals("Result: 0 from 1", msg.content);
	}
	
	@Test
	public void testAddCard() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21", null));
		bot_1.process(new UserMessage(userId_1, "start", null));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "more", null));
		Assert.assertEquals(bj.getMsg(), msg.content);
	}

	@Test
	public void testPlayGameHang() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "hang", null));
		bot_1.process(new UserMessage(userId_1, "start", null));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "о", null));
		Assert.assertEquals(hang.getMsg(), msg.content);
	}
	
	@Test
	public void testPlayGameQuiz() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "quiz", null));
		bot_1.process(new UserMessage(userId_1, "start", null));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "1", null));
		Assert.assertEquals(quiz.getMsg(), msg.content);
	}
	
	@Test
	public void testEndGame() {
		for(String e : games) {
			ChatBot bot_1 = new ChatBot(userId_1);
			bot_1.process(new UserMessage(userId_1, e, null));
			UserMessage msg = bot_1.process(new UserMessage(userId_1, "end", null));
			Assert.assertEquals("Result: 0 from 0", msg.content);
		}
	}
	
	@Test
	public void testEndGameAndCommunit() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21", null));
		bot_1.process(new UserMessage(userId_1, "end", null));
		UserMessage msg = bot_1.process(new UserMessage(userId_1, "sdsd", null));
		Assert.assertEquals("I don't understand you, you can write: help", msg.content);
	}
	
	@Test
	public void testWorkTwoBots() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.process(new UserMessage(userId_1, "21", null));
		ChatBot bot_2 = new ChatBot(userId_2);
		UserMessage msg_2 = bot_2.process(new UserMessage(userId_2, "21", null));
		UserMessage msg_1 = bot_1.process(new UserMessage(userId_1, "help", null));
		Assert.assertEquals(bj.getHelp(), msg_1.content);
		Assert.assertEquals(bj.getBehavior(), msg_2.content);
	}
}
