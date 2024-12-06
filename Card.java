// Harman Dhillon CS145 Lab 2

public class Card {
    String rank;
    String suit;
    int value;

    Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
