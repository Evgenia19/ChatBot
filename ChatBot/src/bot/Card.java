package bot;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Card {

    private static final int numberBeforeSmallCount = 5;
    private static final int differentNumberBetweenCardAndPositionOfArrayForBigCard = 4;
    private static final int getDifferentNumberBetweenCardAndPositionOfArrayForSmallCard = 6;

    public final Rank rank;
    public final Suit suit;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Set<Card> values() {
        Set<Card> cards = new HashSet<>();
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }

    public int getScore() {
        int ordinal = rank.ordinal();
        return ordinal > numberBeforeSmallCount
                ? ordinal - differentNumberBetweenCardAndPositionOfArrayForBigCard
                : ordinal + getDifferentNumberBetweenCardAndPositionOfArrayForSmallCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public String toString() {
        return suit.toString() + " " + rank.toString();
    }
}
