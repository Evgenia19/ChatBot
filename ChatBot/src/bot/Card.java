package bot;


public class Card {
    public final Rank rank;
    public final Suit suit;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card pickRandom() {
        return new Card(Suit.pickRandom(), Rank.pickRandom());
    }

    public String strCard() {
        return suit.toString() + " " + rank.toString();
    }
}
