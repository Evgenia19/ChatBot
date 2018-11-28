package bot;

import java.util.ArrayList;
import java.util.Random;

public class Quiz extends Game{
	
	private Quiz quiz;
	private Random random = new Random();
	private ArrayList<Question> questions;
	private Question current;
	public int score = 0;
	private int questionsLeft;
	private LoadQuestion loadQuestion;
	public int numberQuestions;

	@Override
	public void start() {
		loadQuestion = new LoadQuestion();
		questions = loadQuestion.returnQuestions();
		questionsLeft = questions.size();
		numberQuestions = questions.size();
		chooseQuestion();
	}
	
	@Override
	public String getMsg() {
		return current.question;
	}

	private void chooseQuestion() {
        int index = random.nextInt(questions.size());
        current = questions.get(index);
        questions.remove(index);
        getScore();
    }
	
	private boolean isCorrect(String answer) { 
		return answer.equals(current.rightAnswer); 
	}
	
	public String askQuestion(String content){
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
	
	public void getScore() {
		getGame(numberQuestions);
		System.out.println(numberQuestions);
		getResult(score);
		System.out.println(score);
	}
}
