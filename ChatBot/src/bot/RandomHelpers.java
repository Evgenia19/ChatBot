package bot;

import java.util.concurrent.ThreadLocalRandom;

class RandomHelpers {
	public static int getRandomInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}
}
