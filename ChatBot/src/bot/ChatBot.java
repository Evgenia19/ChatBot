package bot;
import java.io.IOException;
import java.util.*;
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
    private BlackJack blackJack = new BlackJack();
    private Hang hang = new Hang();
    private Quiz quiz = new Quiz();
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
        if(state != ChatBotState.Community) {
            if(msg.content.equals("end")) {
                getStatistic();
                state = ChatBotState.Community;
                return new UserMessage(userId, game.end(), move.get(state));
            }
            return new UserMessage(userId, getCommunicationInGame(msg.content), move.get(state));
        }

        switch(msg.content) {
            case "21":
                state = ChatBotState.BlackJack;
                return new UserMessage(userId, getBehavior21(), move.get(state));
            case "hang":
                state = ChatBotState.Hang;
                return new UserMessage(userId, getBehaviorHang(), move.get(state));
            case "quiz":
                state = ChatBotState.Quiz;
                return new UserMessage(userId, getBehaviorQuiz(), move.get(state));
        }
        return new UserMessage(userId, getCommunicationWithUser(msg.content), move.get(state));
    }

    private String getCommunicationInGame(String msg) {
        return getMsgWhenPlay(msg);
    }

    private String getCommunicationWithUser(String msg) {
        return getMessage(msg);
    }

    private String getBehavior21() {
        game = new BlackJack();
        return game.getBehavior();
    }

    private String getBehaviorHang() {
        game = new Hang();
        return game.getBehavior();
    }

    private String getBehaviorQuiz() {
        game = new Quiz();
        return game.getBehavior();
    }

    private String getMsgWhenPlay(String message) {

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

    private String getMessage(String message) {
        if(weather == true){
            weather = false;
            return  Weather.getWeather(message, model);
        }
        String msg = String.join(" ", message.toLowerCase().split("[ {,|.}?]+"));
        for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
            pattern = Pattern.compile(o.getKey());
            if (pattern.matcher(msg).find()) {
                if("weather".equals(msg))
                    weather = true;
                if("statistic".equals(msg))
                    return statistic.getStatistic();
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
            put("statistic", "statistic");
            put("help", "help");
            // bye
            put("bye", "bye");
        }
    };

    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
        {
            put("hello", "Hi my friend.");
            put("help", "My name Shaaxter. I like community and play game. "
                    + "I can play 21, hang, quiz. If you want play with me then you should write called game");
            put("bye", "Goodbye.");
            put("weather", "Введите город...");
        }
    };

    private String stop21() {
        int sumBot = new Hand(blackJack.getBotHand()).getSum();
        int sumPlayer = new Hand(blackJack.getPlayerHand()).getSum();
        if((sumPlayer >= sumBot | sumBot > 21) & sumPlayer <= 21)
            result += 1;
        Map<String, Integer> result = new HashMap<>();
        result.put("You", sumPlayer);
        result.put("Shaaxter", sumBot);
        return setResult(result);

    }

    private String setResult(Map<String, Integer> result) {
        StringBuilder more21 = new StringBuilder();
        StringBuilder less21 = new StringBuilder();
        StringBuilder good21 = new StringBuilder();
        for(String e : result.keySet()) {
            int sum = result.get(e);
            if (sum > 21) {
                more21.append(e + "-" + sum + " ");
            }
            if (sum < 21) {
                less21.append(e + "-" + sum + " ");
            }
            if (sum == 21)
                good21.append(e + "-" + sum + " ");
        }
        return String.format("Победители, набравшие 21: %s\n" +
                "Набравшие меньше 21: %s\nПеребравшие: %s", good21, less21, more21);
    }

    private String addCard() {
        blackJack.addCard();
        int sumPlayer = new Hand(blackJack.getPlayerHand()).getSum();
        return sumPlayer > 21 ? stop21() : getMessageOfGameForPlayer();
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
        return getMessageOfGameForPlayer();
    }

    private String startHang() {
        games += 1;
        hang = new Hang();
        hang.start();
        return hang.getMessageOfGameForPlayer();
    }

    private String startQuiz() {
        quiz = new Quiz();
        quiz.start();
        return quiz.getMessageOfGameForPlayer();
    }

    private void getStatistic() {
        switch(state) {
            case BlackJack:
                statistic.getStatistic21(games, result);
                break;
            case Hang:
                result = hang.result;
                statistic.getStatisticHang(games, result);
                break;
            case Quiz:
                result = quiz.score;
                games = quiz.numberQuestions;
                statistic.getStatisticQuiz(games, result);
                break;
        }
        games = 0;
        result = 0;
    }

    private String getMessageOfGameForPlayer() {
        Set<Card> playerHand = blackJack.getPlayerHand();
        int score = new Hand(playerHand).getSum();
        return "You card: " + StringHelpers.join(' ', playerHand) + "\n" + "result sum: " + score;
    }

}
