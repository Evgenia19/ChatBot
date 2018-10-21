import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {

	private ChatBotState state;
	private String userId;
	private String id;
	private Map<ChatBotState, String[]> move = new HashMap<ChatBotState, String[]>();
	private String[] answer;
	
	ChatBot(String id) {
		userId = id;
		state = ChatBotState.Community;
		answer = new String[]{"start", "stop", "new game", "end", "more", "help"};
		move.put(ChatBotState.PlayGame, answer);
	}

	public User Users(User user) {
		if(user.content.hashCode() == "start".hashCode())
			state = ChatBotState.PlayGame;
		if(state == ChatBotState.PlayGame) {
			if(user.content.hashCode() == "end".hashCode())
				state = ChatBotState.Community;
			return new User(userId, PlayGame(user.content));
		}
		else
			return new User(userId, Community(user.content));
	}

	private String PlayGame(String msg) {
		return ChatBotControls.gameAnswer(msg);
	}
	
	private String Community(String msg) {
		return ChatBotControls.getMessage(msg);
	}
}