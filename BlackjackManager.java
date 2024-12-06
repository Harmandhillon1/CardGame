// Harman Dhillon CS145 Lab 2

import java.util.ArrayList;
import java.util.Collections;


public class BlackjackManager {
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final int[] VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; // Ace can be 1 or 11

    public ArrayList<Card> deck;
    public ArrayList<Card> playerHand;
    public ArrayList<Card> dealerHand;

    public BlackjackManager() {
        deck = createDeck();
        Collections.shuffle(deck);
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    public ArrayList<Card> createDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (int i = 0; i < RANKS.length; i++) {
                deck.add(new Card(RANKS[i], suit, VALUES[i]));
            }
        }
        return deck;
    }

    public void dealInitialCards() {
        playerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
    }

    public int calculateScore(ArrayList<Card> hand) {
        int score = 0;
        int aces = 0;

        for (Card card : hand) {
            score += card.value;
            if (card.rank.equals("Ace")) {
                aces++;
            }
        }

        while (score > 21 && aces > 0) {
            score -= 10; // Convert Ace from 11 to 1
            aces--;
        }
        return score;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public boolean playerBusted() {
        return calculateScore(playerHand) > 21;
    }

    public void dealerTurn() {
        while (calculateScore(dealerHand) < 17) {
            dealerHand.add(deck.remove(0));
        }
    }
    public Card drawCard() {
        return deck.remove(0);
    }
    
}
