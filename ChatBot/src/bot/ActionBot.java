package bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ActionBot {

    private BlackJack blackJack = new BlackJack();
    private Hang hang = new Hang();
    private Quiz quiz = new Quiz();
    private ChatBotState state;
    private Map<ChatBotState, ArrayList<String>> move = new HashMap<ChatBotState, ArrayList<String>>();
    private ArrayList<String> answer;

    private void getMoving() {
        answer = new ArrayList<String>();
        answer.add("help");
        answer.add("weather");
        answer.add("21");
        answer.add("hang");
        answer.add("quiz");
        state = ChatBotState.Community;
        move.put(state.Community, answer);
        answer = blackJack.getCommands();
        move.put(state.BlackJack, answer);
        answer = hang.getCommands();
        move.put(state.Hang, answer);
        answer = quiz.getCommands();
        move.put(state.Quiz, answer);
    }

    public Map<ChatBotState, ArrayList<String>> returnMove() {
        getMoving();
        return move;
    }


}
