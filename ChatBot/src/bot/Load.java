package bot;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class Load {
    private	String fileQuestion = "questions.txt";
    private	String fileWord = "words.txt";

    private String load(String fileName) {
        String content = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), Charset.forName("UTF-8")))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
        }
        catch (IOException e) {
            //TODO Нужно выбрать другой способ обработки исключений
            //TODO такой?
            e.printStackTrace();
        }
        return content;
    }

    private ArrayList<Question> loadQuestions(String content) {
        ArrayList<Question> questions = new ArrayList<>();
        if(content == null) {

        }
        else {
            for(String q: content.split("\n\n\n")) {
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
        String text = load(fileQuestion);
        return loadQuestions(text);
    }

    private ArrayList<String> loadWords(String content) {
        ArrayList<String> words = new ArrayList<String>();
        if(content == null) {

        }
        else {
            for(String q: content.split("\n"))
                words.add(q);
        }
        return words;
    }

    public ArrayList<String> returnWord() {
        String text = load(fileWord);
        return loadWords(text);
    }
}
