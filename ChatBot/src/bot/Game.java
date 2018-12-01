package bot;

//TODO Нужно переименовать в GameBase или AbstractGame
//TODO Пока вообще не очень понятно, зачем этот абстрактный класс нужен
public abstract class Game implements InterfaceGame{

	private String[] command = new String[] {"start", "new game", "end", "help"};
	
	abstract public void start();
	
	public String[] getCommand() {
		return command;
	}
	
	public void setCommand(String[] com) {
		this.command = com;
	}
	
	private String behavior() {
		String say = "Behavior: ";
		for(String e: command) {
			say += e + "\n";
		}
		return say;
	}
	
	public String getBehavior() {
		return behavior();
	}
	
	public String getHelp() {
		return "My name Shaxter. I like community and play game."
				+ " I can play 21, hang, quiz. If you want play with me then you should write game";
	}
}
