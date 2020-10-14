package ui;

import model.Hangman;

import java.util.Scanner;

public class HangmanGame {

    public HangmanGame() {
        game();
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

    // EFFECTS: return true if the player wants to play again, otherwise stop execution
    public void playAgain() {
        System.out.println("Would you like to play again? [YES or NO]");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.toUpperCase().equals("YES")) {
            game();
        }
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: runs the hangman game
    public void game() {
        Hangman h = new Hangman();
        Ascii ascii = new Ascii();
        h.setupGame();
        ascii.gameIntro();

        while (!(h.getLives() == 0)) {
            ascii.printHangmanAscii(h.getLives(), h.charArrayToString(h.getWordSoFar()));
            System.out.println(h.charArrayToString(h.getAvailableLetters()));
            char guess = guessALetter();
            h.updateAvailableLetters(guess);
            if (h.charInCharArray(guess, h.getHiddenWord())) {
                h.replaceUnderscores(guess);
                if (h.wordComplete()) {
                    ascii.gameWin();
                    playAgain();
                }
            } else {
                h.wrongGuess();
            }
        }
        ascii.gameOver();
        System.out.println("The word/phrase was: " + h.charArrayToString(h.getHiddenWord()));
        playAgain();
    }
}
