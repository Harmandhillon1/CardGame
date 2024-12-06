import java.util.Scanner;
// Harman Dhillon CS145 Lab 2
//This program allows the user to play a game of BlackJack, 
//in which the user is given a set of cards and has a option 
//to hit or stand. Goal is to get hand closet to 21 without 
//crossing it. After user input, a option is displayed to play again or end the game.

public class BlackjackTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            BlackjackManager manager = new BlackjackManager();
            manager.dealInitialCards();

            // Player's turn
            boolean playerBusted = false;
            while (true) {
                System.out.println("Your hand: " + manager.getPlayerHand() + " (Score: " + manager.calculateScore(manager.getPlayerHand()) + ")");
                System.out.println("Dealer's hand: " + manager.getDealerHand().get(0) + " and [Hidden Card]");
                System.out.print("Do you want to hit or stand? (h/s): ");
                String choice = scanner.nextLine().toLowerCase();

                if (choice.equals("h")) {
                    manager.getPlayerHand().add(manager.deck.remove(0));
                    if (manager.playerBusted()) {
                        System.out.println("You busted! Your hand: " + manager.getPlayerHand() + " (Score: " + manager.calculateScore(manager.getPlayerHand()) + ")");
                        playerBusted = true;
                        break;
                    }
                } else if (choice.equals("s")) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose 'h' to hit or 's' to stand.");
                }
            }

            // Dealer's turn
            if (!playerBusted) {
                manager.dealerTurn();
                System.out.println("Dealer's hand: " + manager.getDealerHand() + " (Score: " + manager.calculateScore(manager.getDealerHand()) + ")");

                // Determine winner
                int playerScore = manager.calculateScore(manager.getPlayerHand());
                int dealerScore = manager.calculateScore(manager.getDealerHand());
                System.out.println("Final scores: You: " + playerScore + ", Dealer: " + dealerScore);

                if (dealerScore > 21 || playerScore > dealerScore) {
                    System.out.println("You win!");
                } else if (playerScore < dealerScore) {
                    System.out.println("Dealer wins!");
                } else {
                    System.out.println("It's a tie!");
                }
            }

            // Ask to play again
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing!");
        scanner.close();
 

    }
}
