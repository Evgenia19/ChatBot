package bot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pack
{
    private List<Card> cards;

    public Pack() {
        cards = new ArrayList<>(Card.values());
        RandomHelpers.shuffle(cards);
    }

    public Card pick() {
        return cards.isEmpty() ? null : cards.remove(cards.size() - 1);
    }

    public Collection<Card> pickMany(int count) {
        List<Card> pickedCards = new ArrayList<>();
        while(count > 0 && !cards.isEmpty()) {
            pickedCards.add(cards.remove(cards.size() - 1));
            count--;
        }
        return pickedCards;
    }
}
