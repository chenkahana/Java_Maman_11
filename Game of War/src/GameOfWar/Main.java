package GameOfWar;

import java.util.ArrayList;

public class Main {

    private static boolean playingFlag = true;
    private static int numORounds = 0;


    // the main method fills the deck and shuffles it
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.fillDeck();
        deck.shuffleDeck();
        playWar(deck);
    }

    /**
     *
     * a method that ignits the game.
     * it splits the deck to two players, shuffles them again for good measure and runs the roundOfMethod
     * until a player losses al it's card or a player was already declared a winner from the handleDraw method.
     *
     * @param deck
     */
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

    /**
     *
     * a method that handles a situation were two player drawn the same value card.
     * in this case- both player will draw two cards each and will play another round with another card.
     * the winner of the round after the draw will receive all the cards the were drawn during the round.
     *
     * in case one of the players doesn't have enough card to take part in the draw process- the other player
     *  automatically wins. (that's the logic we use to play by and the assignment didn't specify how to handle this
     *  situation)
     *
     *
     * @param player1Deck
     * @param player2Deck
     * @param p1Card- to be added to the prize of the draw
     * @param p2Card- to be added to the prize of the draw
     */
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

    /**
     *
     * a method that runs a round of war according to the following logic:
     *  each round each player is drawing a card.
     *  the player with the higher value card- wins.
     *  winning the round grants the winner both of the cards that were drawn in the round.
     *
     *  in case that two identical card were presented we will resolve it in the 'handleDraw' method.
     *
     * @param player1Deck
     * @param player2Deck
     * @param drawDeck- a carry from the draw resolution method, if not empty the winner of the round will receive them as well.
     */
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


    /**
     * a method that declares the winner of the game and changes the playing flag to false
     *
     * @param player1Deck
     * @param player2Deck
     */
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
