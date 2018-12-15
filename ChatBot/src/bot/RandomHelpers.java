package bot;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class RandomHelpers {
	public static int getRandomInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public static <T> void shuffle(List<T> source) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = source.size() - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			T a = source.get(index);
			source.set(index, source.get(i));
			source.set(i, a);
		}
	}
}

class StringHelpers {
	public static <T> String join(char delimeter, Iterable<T> iterable) {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for(T element : iterable) {
			if(!first) {
				result.append(delimeter);
			}
			result.append(element.toString());
			first = false;
		}
		return result.toString();
	}
}
