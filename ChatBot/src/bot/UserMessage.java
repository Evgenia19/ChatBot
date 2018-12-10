package bot;

import java.util.ArrayList;

public class UserMessage {

    final String userId;
    final String content;
    final ArrayList<String> commands;

    public UserMessage(String id, String newContent, ArrayList<String> commands) {
        this.userId = id;
        this.content = newContent;
        this.commands = commands;
    }

    public UserMessage(String userId, String content) {
        this(userId, content, null);
    }
}
