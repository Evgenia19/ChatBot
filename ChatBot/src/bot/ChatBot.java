package bot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//TODO Попробуйте проговорить для себя за что именно отвечать ChatBot. Пока для себя я это сформулировать не смог :(
//TODO Ответ на действия пользователя
public class ChatBot {

    private ChatBotState state;
    private String userId;
    private Map<ChatBotState, ArrayList<String>> move;
    private ActionBot moving;
    private AbstractGame game;
    private Statistic statistic;
    private Pattern pattern;
    private BlackJack blackJack;
    private Hang hang;
    private Quiz quiz;
    private int result = 0;
    private int games = 0;
    private boolean weather = false;
    private Model model = new Model();


    ChatBot(String id) {
        userId = id;
        state = ChatBotState.Community;
        statistic = new Statistic();
        moving = new ActionBot();
        move = moving.returnMove();
    }

    public UserMessage process(UserMessage msg) {

        System.out.println(games + result);
        if(state != ChatBotState.Community) {
            if(msg.content.equals("end")) {
                getStatistic();
                games = 0;
                result = 0;
                state = ChatBotState.Community;
                return new UserMessage(userId, game.end(), move.get(state));
            }
            return new UserMessage(userId, play(msg.content), move.get(state));
        }

        switch(msg.content) {
            case "21":
                state = ChatBotState.BlackJack;
                return new UserMessage(userId, behavior21(), move.get(state));
            case "hang":
                state = ChatBotState.Hang;
                return new UserMessage(userId, BehaviorHang(), move.get(state));
            case "quiz":
                state = ChatBotState.Quiz;
                return new UserMessage(userId, BehaviorQuiz(), move.get(state));
        }
        return new UserMessage(userId, communication(msg.content), move.get(state));
    }

    //TODO Неудачное название метода. В методе где-то должен быть глагол.
    private String play(String msg) {
        return game(msg);
    }

    //TODO Неудачное название метода. В методе где-то должен быть глагол.
    private String communication(String msg) {
        return getMessage(msg);
    }

    //TODO Неудачное название метода. В методе где-то должен быть глагол.
    private String behavior21() {
        game = new BlackJack();
        return game.getBehavior();
    }

    //TODO почему с большой буквы?
    private String BehaviorHang() {
        game = new Hang();
        return game.getBehavior();
    }

    //TODO почему с большой буквы?
    //TODO Неудачное
    private String BehaviorQuiz() {
        game = new Quiz();
        return game.getBehavior();
    }

    //TODO Неудачное название метода. В методе где-то должен быть глагол.
    public String game(String message) {

        ArrayList<String> command = move.get(state);
        for(String e: command) {
            if (e.equals(message)) {
                switch(message) {
                    case "A":
                        return quiz.askQuestion(message);
                    case "B":
                        return quiz.askQuestion(message);
                    case "C":
                        return quiz.askQuestion(message);
                    case "statistic":
                        getStatistic();
                        return statistic.getStatistic();
                    case "help":
                        hang = new Hang();
                        return hang.getHelp();
                    case "more":
                        return addCard();
                    case "stop":
                        return stop21();
                    case "start":
                        return start();
                }
            }
            else if (message.length() == 1)
                switch(state) {
                    case Hang:
                        return hang.playHang(message.charAt(0));
                    case Quiz:
                        return quiz.askQuestion(message);
                    default:
                        break;
                }
        }
        return "if you want communication then you should write: end, but if you want play, you should write - start";
    }

    public String getMessage(String message) {
        if(weather == true){
            try {
                weather = false;
                return  Weather.getWeather(message, model);
            } catch (IOException e) {
                return "Город не найден!";
            }
        }
        String msg = String.join(" ", message.toLowerCase().split("[ {,|.}?]+"));
        for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
            pattern = Pattern.compile(o.getKey());
            if (pattern.matcher(msg).find()) {
                if("weather".equals(msg))
                    weather = true;
                return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return "I don't understand you, you can write: help";
    }

    final  Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
        {
            // hello
            put("hi", "hello");
            put("weather", "weather");
            // who // name
            put("help", "help");
            // bye
            put("bye", "bye");
        }
    };

    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
        {
            put("hello", "Hi my friend.");
            put("help", "My name Shaxter. I like community and play game. "
                    + "I can play 21, hang, quiz. If you want play with me then you should write game");
            put("bye", "Goodbey.");
            put("weather", "Введите город...");
        }
    };

    private String stop21() {
        return blackJack.endOfGame();
    }

    private String addCard() {
        return blackJack.addCard();
    }

    private String start() {
        switch(state) {
            case Hang:
                return startHang();
            case Quiz:
                return startQuiz();
            case BlackJack:
                return start21();
            default:
                return null;
        }
    }

    private String start21() {
        games += 1;
        blackJack = new BlackJack();
        blackJack.start();
        return blackJack.messageForPlayer();
    }

    private String startHang() {
        games += 1;
        hang = new Hang();
        hang.start();
        return hang.messageForPlayer();
    }

    private String startQuiz() {
        quiz = new Quiz();
        quiz.start();
        return quiz.messageForPlayer();
    }

    private void getStatistic() {
        switch(state) {
            case BlackJack:
                result += blackJack.result;
                statistic.getStatistic21(games, result);
                break;
            case Hang:
                result += hang.result;
                statistic.getStatisticHang(games, result);
                break;
            case Quiz:
                result += quiz.score;
                statistic.getStatisticQuiz(games, result);
                break;
        }
    }
}
