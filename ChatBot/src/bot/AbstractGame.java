package bot;

import java.util.ArrayList;

public abstract class AbstractGame implements Game{

    private ArrayList<String> command = new ArrayList<String>();

    abstract public void start();
    abstract public String messageForPlayer();

    public String end() {
        return "let's speak";
    }

    public ArrayList<String> getCommands() {
        return command;
    }

    public void setCommands(ArrayList<String> com) {
        this.command = com;
    }

    private String behavior() {
        StringBuilder say = new StringBuilder();
        say.append("Behavior: ");
        for(String e: command) {
            say.append(e + "\n");
        }
        return say.toString();
    }

    public String getBehavior() {
        return behavior();
    }

    public String getHelp() {
        return "My name Shaxter. I like community and play game."
                + " I can play 21, hang, quiz. If you want play with me then you should write game";
    }
}
