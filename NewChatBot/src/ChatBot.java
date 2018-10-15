public class ChatBot {

	private String userId;
	private String id;
	
	ChatBot(String id) {
		userId = id;
	}

	public User Users(User user) {
		String msg = ChatBotControls.getMassage(user.content);
		return new User(userId, msg);
	}

}
