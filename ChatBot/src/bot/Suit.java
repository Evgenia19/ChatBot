package bot;

public enum Suit {
	Diamonds, Hearts, Spades, Clubs;

	private static final Suit[] Values = values();
	private static final int Size = Values.length;

	public static Suit pickRandom() {
		return Values[RandomHelpers.getRandomInt(Size)];
	}
}
