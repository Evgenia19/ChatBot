import org.junit.Assert;
import org.junit.Test;

public class BotTests {
	private String userId_1 = "one";
	private String userId_2 = "two";
	private BlackJack bj= new BlackJack();
	
	@Test
	public void testCreate() {
		new ChatBot("one");
	}

	private ChatBot createStartedBot_1(String uId) {
		ChatBot bot = new ChatBot(userId_1);
		bot.Users(new User(userId_1, "привет"));
		return bot;
	}

	private ChatBot createStartedBot_2(String uId) {
		ChatBot bot = new ChatBot(userId_2);
		bot.Users(new User(userId_2, "привет"));
		return bot;
	}

	@Test
	public void testStarts() {
		ChatBot bot_1 = new ChatBot(userId_1);
		User msg = bot_1.Users(new User(userId_1, "привет"));
		Assert.assertEquals("Здравствуйте, рад Вас видеть.", msg.content);
	}

	@Test
	public void testHelps() {
		ChatBot bot_1 = new ChatBot(userId_1);
		User msg = bot_1.Users(new User(userId_1, "ты кто"));
		Assert.assertEquals(
				"Я обычный чат-бот. Зовите меня Шахттер :). "
						+ "Я очень много знаю о шахматах, и хочу проверить, сколько, Вы, знаете?! "
						+ "Я буду играть с вами в 21. "
						+ "Если хотите начать, то пишите 21. ",
				msg.content);
	}

	@Test
	public void testOnNotUderstand() {
		ChatBot bot_1 = new ChatBot(userId_1);
		User msg = bot_1.Users(new User(userId_1, "asas"));
		Assert.assertEquals("Не понимаю о чем вы, лучше спросите кто я", msg.content);
	}

	@Test
	public void testGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		User msg = bot_1.Users(new User(userId_1, "21"));
		Assert.assertEquals("Правила: " + "\n" + "start - начать игру" + "\n" + "new game - ноавая игра" + "\n"
				+ "more - ещё карту" + "\n" + "stop - хватить", msg.content);
	}
	
	@Test
	public void testStartGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		User msg = bot_1.Users(new User(userId_1, "start"));
		Assert.assertEquals(bj.getMessage(), msg.content);
	}

	@Test
	public void testNewGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		User msg = bot_1.Users(new User(userId_1, "new game"));
		Assert.assertEquals(bj.getMessage(), msg.content);
	}

	@Test
	public void testHelpsWhileGame() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.Users(new User(userId_1, "21"));
		bot_1.Users(new User(userId_1, "start"));
		User msg = bot_1.Users(new User(userId_1, "ты кто"));
		Assert.assertEquals(
				"Я обычный чат-бот. Зовите меня Шахттер :). "
						+ "Я очень много знаю о шахматах, и хочу проверить, сколько, Вы, знаете?! "
						+ "Я буду играть с вами в 21. "
						+ "Если хотите начать, то пишите 21. ",
				msg.content);
	}

	@Test
	public void testWorkTwoBots() {
		ChatBot bot_1 = new ChatBot(userId_1);
		bot_1.Users(new User(userId_1, "привет"));
		ChatBot bot_2 = new ChatBot(userId_2);
		User msg_2 = bot_2.Users(new User(userId_2, "привет"));
		User msg_1 = bot_1.Users(new User(userId_1, "ты кто"));
		Assert.assertEquals(
				"Я обычный чат-бот. Зовите меня Шахттер :). "
						+ "Я очень много знаю о шахматах, и хочу проверить, сколько, Вы, знаете?! "
						+ "Я буду играть с вами в 21. "
						+ "Если хотите начать, то пишите 21. ",
				msg_1.content);
		Assert.assertEquals("Здравствуйте, рад Вас видеть.", msg_2.content);
	}
}
