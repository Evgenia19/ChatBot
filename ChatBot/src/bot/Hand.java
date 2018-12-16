package bot;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Hand {

    private Set<Card> cards = new HashSet<>();
    final int sum;

    Hand(Collection<Card> hand) {
        cards.addAll(hand);
        sum = cards.stream().mapToInt(Card::getScore).sum();
    }

    public void addCard(Card e) {
        cards.add(e);
    }

    public Set<Card> getCards(){
        return cards;
    }

    public String toString() {
        return cards.toString();
    }
}
