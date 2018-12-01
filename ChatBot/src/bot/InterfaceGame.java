package bot;

//TODO Нужно переименовать в Game
interface InterfaceGame {
	//TODO Метод возвращает много комманд, поэтому, его стоит называть getCommands
	String[] getCommand();
	void start();
}
