package ui;

import model.Hangman;

import java.util.Scanner;

public class UserInput {

    public UserInput() {
    }

    // EFFECTS: return true if the player wants to play again, otherwise stop execution
    public boolean playAgain() {
        System.out.println("Would you like to play again? [YES or NO]");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.toUpperCase().equals("YES")) {
            return true;
        }
        System.exit(0);
        return false;
    }


    // MODIFIES: this
    // EFFECTS: asks a user to "guess a letter", then returns the guess as a character
    public char guessALetter() {
        System.out.println("Guess a Letter:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        char guess = input.charAt(0);
        return Character.toUpperCase(guess);
    }

}
