package bot;
import java.util.Map;

//TODO Попробуйте проговорить для себя за что именно отвечать ChatBot. Пока для себя я это сформулировать не смог :(
public class ChatBot {

	private ChatBotState state;
	private String userId;
	private Map<ChatBotState, String[]> move;
	private ChatBotControl chatBotControls;
	private Moving moving;
	//TODO Неиспользуемые переменные
	private BlackJack blackJack;
	private Game game;
	private ChatBotState lastState;
	
	
	ChatBot(String id) {
		userId = id;
		state = ChatBotState.Community;
		chatBotControls = new ChatBotControl();
		moving = new Moving();
		move = moving.returnMove();
	}

	public UserMessage process(UserMessage msg) {
		
		if(state != ChatBotState.Community) {
			if(msg.content.equals("end")) {
				lastState = state;
				state = ChatBotState.Community;
				String text = chatBotControls.game(msg.content, move, lastState);
				return new UserMessage(userId, text, move.get(lastState));
			}
			return new UserMessage(userId, play(msg.content), move.get(state));
		}
		
		switch(msg.content) {
		case "21":
			state = ChatBotState.BlackJack;
			return new UserMessage(userId, behavior21(blackJack), move.get(state));
		case "hang":
			state = ChatBotState.Hang;
			return new UserMessage(userId, getBehavior(game), move.get(state));
		case "quiz":
			state = ChatBotState.Quiz;
			return new UserMessage(userId, getBehavior(game), move.get(state));
		}
		return new UserMessage(userId, communication(msg.content), null);
	}

	private String play(String msg) {
		return chatBotControls.game(msg, move, state);
	}

	private String communication(String msg) {
		return chatBotControls.getMessage(msg);
	}
	
	private String behavior21(BlackJack game) {
		game = new BlackJack();
		return game.getBehavior();
	}
	
	private String getBehavior(Game game) {
		game = new Hang();
		return game.getBehavior();
	}
}
