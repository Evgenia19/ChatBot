import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetQuestion {

	public static Map<String,Integer> getQuestion = new HashMap<String,Integer>();
	

	public static String loadQuestions(String text) {
		int substring = 0;
    	String content = null;
		try {
            Scanner reader = new Scanner(new File(text));
            content = reader
            		.useDelimiter("\\A")
            		.next();
            reader.close();
        } catch (IOException e) {
            System.out.println("не удалось открыть questions.txt ");
        }
		if(getQuestion.containsKey(text))
			substring = getQuestion.get(text);
		String question = "";
		String answer = "";
        String[] questions = content.split("\r\n\r\n");
		for (int i = substring; i < 5 + substring; i++) {
			if(i == 4)
				answer = questions[i];
			else
				question += questions[i];
		}
		getQuestion.put(text, substring + 5);
		return (question + answer);
    }
}
