package bot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {

	private ChatBotState state;
	private GameState game;
	private String userId;
	private String[] comand;
	private Map<ChatBotState, String[]> move = new HashMap<ChatBotState, String[]>();
	private Map<GameState, String[]> moveGame = new HashMap<GameState, String[]>();
	private String[] answer;
	private ChatBotControl chatBotControls;
	
	ChatBot(String id) {
		userId = id;
		state = ChatBotState.Community;
		answer = new String[]{"21", "hang", "quiz"};
		move.put(state.PlayGame, answer);
		answer = new String[]{"start", "stop", "new game", "end", "more", "help"};
		moveGame.put(game.BlackJack, answer);
		answer = new String[]{"start","new game", "end", "help"};
		moveGame.put(game.Hang, answer);
		chatBotControls = new ChatBotControl();
	}

	public UserMessage process(UserMessage msg) {
		if(msg.content.equals("start"))
			state = ChatBotState.PlayGame;
		else if(msg.content.equals("21"))
			game = GameState.BlackJack;
		else if(msg.content.equals("hang"))
			game = GameState.Hang;
		
		if(state == ChatBotState.PlayGame) {
			if(msg.content.equals("end"))
				state = ChatBotState.Community;
			if(game == GameState.BlackJack)
				return new UserMessage(userId, play21(msg.content), moveGame.get(game.BlackJack));
			else if(game == GameState.Hang)
				return new UserMessage(userId, playHang(msg.content), moveGame.get(game.Hang));
		}
		return new UserMessage(userId, community(msg.content), move.get(state.PlayGame));
	}

	private String play21(String msg) {
		return chatBotControls.game21(msg);
	}
	
	private String playHang(String msg) {
		return chatBotControls.gameHang(msg);
	}
	
	private String community(String msg) {
		return chatBotControls.getMessage(msg);
	}
}