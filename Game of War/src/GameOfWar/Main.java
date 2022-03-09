package GameOfWar;

import java.util.ArrayList;

public class Main {

    private static boolean playingFlag = true;
    private static int numORounds = 0;

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.fillDeck();
        deck.shuffleDeck();
        playWar(deck);
    }

    public static void playWar(DeckOfCards deck) {
        System.out.println("LETS PLAY WAR!!");
        ArrayList<DeckOfCards> decks = deck.divideDeckToPlayers(2);

        DeckOfCards player1Deck = decks.get(0);
        DeckOfCards player2Deck = decks.get(1);

        player1Deck.shuffleDeck();
        player2Deck.shuffleDeck();

        while (playingFlag && player1Deck.getNumOfCards() > 0 && player2Deck.getNumOfCards() > 0) {
            roundOfWar(player1Deck, player2Deck, new DeckOfCards());
        }
        if (playingFlag)
            declareWinner(player1Deck, player2Deck);
    }

    public static void handleDraw(DeckOfCards player1Deck, DeckOfCards player2Deck, Card p1Card, Card p2Card) {
        if (player1Deck.getNumOfCards() >= 3 && player2Deck.getNumOfCards() >= 3) {
            DeckOfCards drawDeck = new DeckOfCards();

            drawDeck.addToDeck(p1Card);
            drawDeck.addToDeck(p2Card);

            for (int i = 0; i < 2; i++) {
                drawDeck.addToDeck(player1Deck.getCard());
                drawDeck.addToDeck(player2Deck.getCard());
            }
            roundOfWar(player1Deck, player2Deck, drawDeck);
        } else {
            if (player1Deck.getNumOfCards() < player2Deck.getNumOfCards()) {
                System.out.println("Player 1 doesn't have enough card to draw, so....");
            } else {
                System.out.println("Player 2 doesn't have enough card to draw, so....");
            }
            declareWinner(player1Deck, player2Deck);
        }


    }

    public static void roundOfWar(DeckOfCards player1Deck, DeckOfCards player2Deck, DeckOfCards drawDeck) {
        numORounds++;
        Card p1Card = player1Deck.getCard();
        Card p2Card = player2Deck.getCard();
        System.out.println("==========================");
        System.out.println("Player 1 has: " + p1Card);
        System.out.println("Player 2 has: " + p2Card);

        int p1CardValue = p1Card.getNumberOfValue();
        int p2CardValue = p2Card.getNumberOfValue();

        if ((p1CardValue > p2CardValue || p1CardValue == 1) && p2CardValue != 1) {   //ACE'S VALUE IS 1 BUT IT'S THE STRONGEST
            player1Deck.addToDeck(p1Card);
            player1Deck.addToDeck(p2Card);
            player1Deck.addToDeck(drawDeck);
            System.out.println("Player 1 takes round\n");

        } else if (p1CardValue < p2CardValue || p2CardValue == 1) {  //ACE'S VALUE IS 1 BUT IT'S THE STRONGEST
            player2Deck.addToDeck(p1Card);
            player2Deck.addToDeck(p2Card);
            player2Deck.addToDeck(drawDeck);
            System.out.println("Player 2 takes round\n");

        } else {
            System.out.println("DRAW\n");
            handleDraw(player1Deck, player2Deck, p1Card, p2Card);
        }
    }


    public static void declareWinner(DeckOfCards player1Deck, DeckOfCards player2Deck) {
        System.out.println("In " + numORounds + " rounds:");
        playingFlag = false;
        if (player1Deck.getNumOfCards() > player2Deck.getNumOfCards()) {
            System.out.println("PLAYER 1 WINS!!!");
            return;
        }
        System.out.println("PLAYER 2 WINS!!!");
    }


}
