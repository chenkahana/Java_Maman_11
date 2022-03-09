package GameOfWar;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    private ArrayList<Card> deck;

    public DeckOfCards() {
        deck = new ArrayList<>();
    }

    public DeckOfCards(ArrayList<Card> deck) {
        setDeck(deck);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public int getNumOfCards() {
        return deck.size();
    }

    public void addToDeck(Card card) {
        this.deck.add(deck.size(), card);
    }

    /**
     * adds a card to the 'bottom' of the deck
     *
     * @param cards
     */
    public void addToDeck(DeckOfCards cards) {
        while (cards.getNumOfCards() > 0) {
            deck.add(cards.getCard());
        }
    }

    /**
     * a method that returns the card from the 'top' of the deck
     * and return null if the deck is empty
     *
     * @return the first Card from the deck
     */
    public Card getCard() {
        if (deck.size() > 0) {
            Card card = deck.get(0);
            deck.remove(0);
            return card;
        }
        return null;
    }

    /**
     * a method that fills the deck with all the 52 card (excluding the two jokers)
     */
    public void fillDeck() {
        for (int i = 0; i < 52; i++) {
            int value = (i + 1) % 13 == 0 ? 13 : (i + 1) % 13;
            if (i <= 12) {
                addToDeck(new Card(Symbol.CLUBS, value));
            } else if (i <= 25) {
                addToDeck(new Card(Symbol.DIAMONDS, value));
            } else if (i <= 38) {
                addToDeck(new Card(Symbol.HEARTS, value));
            } else {
                addToDeck(new Card(Symbol.SPADES, value));
            }
        }
    }


    /**
     * a method that shuffles the order of the deck
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * a method that returns an ArrayList with an equal sized deck in each index of the ArrayList
     * number of index in the returned ArrayList is determent by the received argument.
     * @param numOfPlayers
     * @return returns an ArrayList with an equal sized deck in each index of the ArrayList
     */
    public ArrayList<DeckOfCards> divideDeckToPlayers(int numOfPlayers) {
        ArrayList<DeckOfCards> decks = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            int from = i * (deck.size() / numOfPlayers);
            int to = i * (deck.size() / numOfPlayers) + (deck.size() / numOfPlayers);
            decks.add(new DeckOfCards(new ArrayList<>(deck.subList(from, to))));
        }
        return decks;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < deck.size(); i++) {
            ret.append(deck.get(i).toString() + " , ");
        }
        return ret.toString();
    }
}
