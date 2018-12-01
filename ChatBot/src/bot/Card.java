package bot;


public class Card {
	public final Rank rank;
	public final Suit suit;
    
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	//TODO Надо заиспользовать этот метод
	public static Card pickRandom() {
		return new Card(Suit.pickRandom(), Rank.pickRandom());
	}
}
