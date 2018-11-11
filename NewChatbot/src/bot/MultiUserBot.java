package bot;
import java.util.HashMap;
import java.util.Map;

public class MultiUserBot {

	private Map<String, ChatBot> bots;

	public MultiUserBot() {
		bots = new HashMap<String, ChatBot>();
	}

	public UserMessage users(UserMessage user) {
		ChatBot handler = bots.get(user.userId);
		return handler.process(user);
	}
	
	public boolean addUser(String id) {
		if (!bots.containsKey(id)) {
			bots.put(id, new ChatBot(id));
			return true;
		}
		else 
			return false;
	}
}