package bot;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		MultiUserBot multiUser = new MultiUserBot();
		String id = null;
		Scanner input = new Scanner(System.in);
		System.out.println("Shaaxter: Please, choose your id");
		while (true) {
			try {
				if (input.hasNext()) {
					String msg = input.nextLine();
					if (id == null) {
						if (multiUser.addUser(msg)) {
							id = msg;
							System.out.println("\n" + "Shaaxter: ok" + "\n\n" + id + ": ");
						} else
							System.out.println("\n" + "Shaaxter: There is this id, choose different");
					} else {
						UserMessage message = multiUser.users(new UserMessage(id, msg, null));
						System.out.println("\n" + "Shaaxter: " + message.content + "\n\n" + id + ": ");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
