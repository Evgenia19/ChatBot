package bot;

import java.util.HashMap;
import java.util.Map;

public class Moving {
	
	private BlackJack blackJack = new BlackJack();
	private Hang hang = new Hang();
	private Quiz quiz = new Quiz();
	private ChatBotState state;
	private Map<ChatBotState, String[]> move = new HashMap<ChatBotState, String[]>();
	private String[] answer;
	
	private void getMoving() {
		state = ChatBotState.Community;
		answer = blackJack.getCommand();
		move.put(state.BlackJack, answer);
		answer = hang.getCommand();
		move.put(state.Hang, answer);
		answer = quiz.getCommand();
		move.put(state.Quiz, answer);
	}

	public Map<ChatBotState, String[]> returnMove() {
		getMoving();
		return move;
	}
	
	
}
