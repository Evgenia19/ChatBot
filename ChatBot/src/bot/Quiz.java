package bot;

import java.util.ArrayList;
import java.util.Random;

public class Quiz extends AbstractGame{

    //TODO Вы теперь можете воспользоваться RandomHelpers и не писать это
    private Random random = new Random();
    private ArrayList<Question> questions;
    private Question current;
    public int score = 0;
    private int questionsLeft;
    private Load loadQuestion;
    public int numberQuestions = 0;
    private ArrayList<String> command;

    Quiz() {
        command = getCommands();
        returnCommandsOfGame();
    }

    @Override
    public void start() {
        loadQuestion = new Load();
        questions = loadQuestion.returnQuestions();
        questionsLeft = questions.size();
        //TODO Это чего еще за печатание на экран?! Все взаимодействие с пользователем должны быть вынесено из логики
        System.out.println(questionsLeft);
        chooseQuestion();
    }

    private void returnCommandsOfGame() {
        command.add(1, "A");
        command.add(2, "B");
        command.add(3, "C");
        setCommands(command);
    }

    public String messageForPlayer() {
        return current.question;
    }

    private void chooseQuestion() {
        int index = random.nextInt(questions.size());
        current = questions.get(index);
        questions.remove(index);
    }

    private boolean isCorrect(String answer) {
        return answer.equals(current.rightAnswer);
    }

    public String askQuestion(String content){
        numberQuestions += 1;
        if (isCorrect(content)) {
            score++;
            questionsLeft--;
            if (questionsLeft == 0)
                return "Верно! Ура, викторина наконец-то закончилась!";
            chooseQuestion();
            return "Верно! Следующий вопрос: \n" +  current.question;
        }
        chooseQuestion();
        return "Неправильно :с ." + current.question;
    }
}
