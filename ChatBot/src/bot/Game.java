package bot;

public abstract class Game implements InterfaceGame{

	private String[] command = new String[] {"start", "new game", "end game", "help"};
	private int result;
	private int numberGame;
	
	
	abstract public void start();
	abstract public String getMsg();
	
	public String[] getCommand() {
		return command;
	}
	
	public void setCommand(String[] com) {
		this.command = com;
	}
	
	public void getResult(int res) {
		this.result = res;
	}
	
	public void getGame(int numGame) {
		this.numberGame = numGame;
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
	
	public String end() {
		String say = "Result: " + result + " from " + numberGame;
		return say;
	}
}
