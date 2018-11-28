package bot;


public class Card {

	final NumberCard number;
	final SuitCard suit;
    
	public Card(SuitCard suit, NumberCard num) {
		this.suit = suit;
		this.number = num;
	}
	
	public int getNumber() {
		return number.ordinal();
	}
	
	public String getCard(SuitCard suit, NumberCard num) {
		return suit.toString() + " " + num.toString();
	}
}
