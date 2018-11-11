package bot;
public class UserMessage {

	final String userId;
	final String content;
	final String[] comand;

	public UserMessage(String id, String newContent, String[] comand) {
		this.userId = id;
		this.content = newContent;
		this.comand = comand;
	}
}