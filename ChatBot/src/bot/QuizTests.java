package bot;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class QuizTests {

    private Quiz quiz = new Quiz();
    private ArrayList<Question> questions;
    private Question question;

    @Test
    public void getQuestionTest() {
        questions = quiz.getQuestions();
        question = questions.get(0);
        Assert.assertEquals(question.question, "Сколько клеток на шахматной доске?\n" +
                "A) 64\n" +
                "B) 48\n" +
                "C) 36");
    }

    @Test
    public void getRightAnswerTest() {
        quiz.start();
        Assert.assertEquals(quiz.askQuestion("A"), "Верно! Следующий вопрос: \nОткуда пришли шахматы?\n" +
                "A) Египет\n" +
                "B) Индия\n" +
                "C) Персия");
    }

    @Test
    public void getWrongAnswerTest() {
        quiz.start();
        Assert.assertEquals(quiz.askQuestion("B"), "Неправильно :с .\nОткуда пришли шахматы?\n" +
                "A) Египет\n" +
                "B) Индия\n" +
                "C) Персия");
    }

    @Test
    public void getEndDameTest() {
        quiz.start();
        for(int i = 0; i <= 7; i++){
            quiz.askQuestion("A");
        }
        quiz.askQuestion("A");
        Assert.assertEquals(quiz.askQuestion("A"), "Верно! Ура, викторина наконец-то закончилась!");
    }

    @Test
    public void loadQuestionsTest() {

    }

    @Test
    public void getMessageTest() {

    }
}
