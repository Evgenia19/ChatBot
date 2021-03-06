package bot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class LoadFiles {

    private static Logger logger = LogManager.getLogger(LoadFiles.class.getName());
    private	String fileQuestion = "questions.txt";
    private	String fileWord = "words.txt";

    private String load(String fileName) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), Charset.forName("UTF-8")))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
        }
        catch (IOException e) {
            logger.warn("warn error message: " + e.getMessage());
            return "Проблемы с загрузкой...";
        }
        if (content == null)
            return "Файлы пустые";
        return content.toString();
    }

    private ArrayList<Question> loadQuestions(String content) {
        ArrayList<Question> questions = new ArrayList<>();
        for(String q: content.split("\n\n\n")) {
            questions.add(makeQuestion(q));
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
        for(String q: content.split("\n"))
                words.add(q);
        return words;
    }

    public ArrayList<String> returnWord() {
        String text = load(fileWord);
        return loadWords(text);
    }
}
