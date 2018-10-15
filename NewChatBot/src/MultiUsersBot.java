import java.util.HashMap;
import java.util.Map;

public class MultiUsersBot {

	private static Map<String, ChatBot> bots;

	public MultiUsersBot() {
		bots = new HashMap<String, ChatBot>();
	}

	public User Users(User user) {
		ChatBot handler = bots.get(user.userId);
		return handler.Users(user);
	}
	
	public Boolean addUser(String id) {
		if (!bots.containsKey(id)) {
			bots.put(id, new ChatBot(id));
			return true;
		}
		else 
			return false;
	}
}