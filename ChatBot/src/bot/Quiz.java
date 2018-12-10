package bot;

import java.util.ArrayList;
import java.util.Random;

public class Quiz extends AbstractGame{

    private ArrayList<Question> questions;
    private Question current;
    public int score = 0;
    private int questionsLeft;
    private LoadFile loadQuestion;
    public int numberQuestions = 0;
    private ArrayList<String> command;

    Quiz() {
        command = getCommands();
        returnCommandsOfGame();
        loadQuestion = new LoadFile();
        questions = loadQuestion.returnQuestions();
        questionsLeft = questions.size();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public void start() {
        chooseQuestion();
    }

    private void returnCommandsOfGame() {
        command.add("start");
        command.add("A");
        command.add("B");
        command.add("C");
        command.add("end");
        command.add("statistic");
        command.add("help");
        setCommands(command);
    }

    public String messageForPlayer() {
        return current.question;
    }

    private void chooseQuestion() {
        int index = 0;
        current = questions.get(index);
        questions.remove(index);
    }

    private boolean isCorrect(String answer) {
        return answer.equals(current.rightAnswer);
    }

    public String askQuestion(String content){
        numberQuestions++;
        questionsLeft--;
        if (questionsLeft == 0) {
            if (isCorrect(content)) {
                score++;
                return "Верно! Ура, викторина наконец-то закончилась!";
            }
            return "Неверно! Ура, викторина наконец-то закончилась!";
        }
        if (isCorrect(content)) {
            score++;
            chooseQuestion();
            return "Верно! Следующий вопрос: \n" +  current.question;
        }
        chooseQuestion();
        return "Неправильно :с ." + current.question;
    }
}
