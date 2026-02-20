import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;

public class Deck {
    private PlayingCard[] deck;
    private int numCards;

    public Deck() {
        deck = new PlayingCard[52];
    }

    /*
    public PlayingCard getCardAt(int index) {
        return deck[index];
    }
     */

    public void addCard(PlayingCard card) {
        if (numCards >= 52) {
            throw new IllegalStateException("Too many cards in the deck!");
        } else {
            deck[numCards++] = card;
        }
    }

    public PlayingCard removeCard() {
        if (numCards == 0) {
            return null;
        }

        PlayingCard temp = deck[0];
        for (int i = 0; i < numCards - 1; i++) {
            deck[i] = deck[i + 1];
        }

        deck[--numCards] = null;

        return temp;
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = numCards - 1; i > 0; i--) {
            PlayingCard temp = deck[i];
            int newRand = rand.nextInt(i + 1);
            deck[i] = deck[newRand];
            deck[newRand] = temp;
        }
    }

    public void sort () {
        for (int i = 0; i < numCards - 1; i++) {
            for (int j = i + 1; j < numCards; j++) {
                if (deck[i].getSuit() == deck[j].getSuit()) {
                    if (deck[i].getRank().getValue() >= deck[j].getRank().getValue()) {
                        PlayingCard temp = deck[i];
                        deck[i] = deck[j];
                        deck[j] = temp;
                    }
                } else if (deck[i].getSuit().ordinal() > deck[j].getSuit().ordinal()) {
                    PlayingCard temp = deck[i];
                    deck[i] = deck[j];
                    deck[j] = temp;
                }
            }
        }
    }
}