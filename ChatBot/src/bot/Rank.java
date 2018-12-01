package bot;

public enum Rank {
	Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;

	private static final Rank[] Values = values();
	private static final int Size = Values.length;

	public static Rank pickRandom() {
		return Values[RandomHelpers.getRandomInt(Size)];
	}
}

