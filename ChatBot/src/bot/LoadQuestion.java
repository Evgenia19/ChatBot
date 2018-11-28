package bot;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class LoadQuestion {
	private	String fileName = "questions.txt";
	
	private ArrayList<Question> loadQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        String content = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), Charset.forName("UTF-8")))) {			
			String line = "";
			while ((line = reader.readLine()) != null) {
				content += line + "\n";
			}
        } 
        catch (IOException e) {
        	System.out.println(e.getMessage());
        }
        if(content == null)
        	System.out.println("Файл пуст");
        else {
        	for(String q: content.split("\n\n\n")) {
        		System.out.println(q + "skasika");
        		System.out.println(makeQuestion(q));
                questions.add(makeQuestion(q));
        	}
        }
        return questions;
    }
	
    private Question makeQuestion(String raw) {
        String[] lines = raw.split("\n\n");
        String text = lines[0];
        if (lines.length != 2)
        	return new Question(null, null);
        return new Question(text, lines[1]);
    }
    
    public ArrayList<Question> returnQuestions() {
    	return loadQuestions();
    }
}
