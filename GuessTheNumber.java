package Java;

import java.util.*;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int totalScore = 0;
        int round = 1;
        boolean playAgain = true;

        System.out.println("===== WELCOME TO GUESS THE NUMBER GAME =====");

        while (playAgain) {
            int numberToGuess = rand.nextInt(100) + 1; // 1 to 100
            int attemptsLeft = 7;  // you can change attempt limit
            int attemptsUsed = 0;
            boolean guessedCorrect = false;

            System.out.println("\n--- ROUND " + round + " ---");
            System.out.println("I have generated a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts. Good luck!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attemptsUsed++;

                if (guess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number!");

                    // Points based on attempts
                    int points = Math.max(0, 10 - attemptsUsed);
                    System.out.println("You earned " + points + " points this round.");

                    totalScore += points;
                    guessedCorrect = true;
                    break;
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrect) {
                System.out.println("\n❌ Out of attempts!");
                System.out.println("The correct number was: " + numberToGuess);
            }

            System.out.println("\nYour total score so far: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String choice = sc.next().toLowerCase();

            if (!choice.equals("yes")) {
                playAgain = false;
            } else {
                round++;
            }
        }

        System.out.println("\n===== GAME OVER =====");
        System.out.println("Final Score: " + totalScore);
        System.out.println("Thanks for playing!");
        
        sc.close();
    }
}
