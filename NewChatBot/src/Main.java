import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		MultiUserBot multiUser = new MultiUserBot();
		String id = null;
		Scanner input = new Scanner(System.in);
		System.out.println("Шаахтер: Введите ваш ник");
		while (true) {
			//try {
				if (input.hasNext()) {
					String msg = input.nextLine();
					if (id == null) {
						if (multiUser.addUser(msg)) {
							id = msg;
							//System.out.println(id + ": " + id);
							System.out.println("\n" + "Шаахтер: Рад свами познакомится" + "\n\n" + id + ": ");
						} else
							System.out.println("\n" + "Шаахтер: Введите другой ник, такой уже есть");
					} else {
						//System.out.println(id + ": " + msg);
						User message = multiUser.Users(new User(id, msg));
						System.out.println("\n" + "Шаахтер: " + message.content + "\n\n" + id + ": ");
					}
				}
			//} catch (Exception e) {
				//e.printStackTrace();
			//}
		}
	}
}
