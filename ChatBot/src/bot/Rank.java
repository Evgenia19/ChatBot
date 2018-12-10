package bot;

public enum Rank {
	Six, Seven, Eight, Nine, Ten, Ace, Jack, Queen, King;

	private static final Rank[] Values = values();
	private static final int Size = Values.length;

	public static Rank pickRandom() {
		return Values[RandomHelpers.getRandomInt(Size)];
	}
}

