import java.util.HashMap;
import java.util.Map;

public class MultiUser {
	private Map<String, ChatBot> bots;
	public MultiUser() {
		Map<String, ChatBot> bots = new HashMap<String, ChatBot>();
	}

	//public Message respondTo(Message m) {
		//if (!bots.containsKey(m.userId))
			//bots.put(m.userId, new ChatBot(m.userId));
		//ChatBot handler = bots.get(m.userId);
	//	return handler.respondTo(m);
	//}
}