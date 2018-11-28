package bot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {

	private ChatBotState state;
	private String userId;
	private Map<ChatBotState, String[]> move;
	private String[] commands;
	private ChatBotControl chatBotControls;
	private Moving moving;
	private BlackJack blackJack;
	private Game game;
	
	
	ChatBot(String id) {
		userId = id;
		state = ChatBotState.Community;
		chatBotControls = new ChatBotControl();
		moving = new Moving();
		move = moving.returnMove();
		for(ChatBotState e : move.keySet())
			System.out.println(move.get(e));
	}

	public UserMessage process(UserMessage msg) {
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
		default:
			break;
		}
		
		if(state != ChatBotState.Community) {
			if(msg.content.equals("end"))
				state = ChatBotState.Community;
			return new UserMessage(userId, play(msg.content), move.get(state));
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
