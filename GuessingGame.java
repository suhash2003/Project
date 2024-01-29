package com.project;

import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int totalAttempts = 0;
        int totalRounds = 0;
        
        System.out.println("Welcome to the Guessing Game!");
        
        boolean playAgain = true;
        while (playAgain) {
            totalRounds++;
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("\nRound " + totalRounds + ":");
            System.out.println("Guess the number between " + minRange + " and " + maxRange + ".");
            
            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                
                if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number correctly: " + randomNumber);
                    guessedCorrectly = true;
                    break;
                }
                
                attempts++;
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The number was: " + randomNumber);
            }
            
            totalAttempts += (guessedCorrectly ? 1 : 0);
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.nextLine();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
        }
        
        scanner.close();
        System.out.println("Game over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total correct guesses: " + totalAttempts);
    }
}
