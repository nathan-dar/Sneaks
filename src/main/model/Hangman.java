package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public String[] wordBank = {"TESTING"};
    char[] wordSoFar;
    char[] hiddenWord;
    public int lives = 5;

    public Hangman() {
    }

    // MODIFIES: this
    // EFFECTS: selects a random word from the word bank and converts it into a character array
    public void randomWord() {
        Random r = new Random();
        int randomInt = r.nextInt(wordBank.length);
        String str = wordBank[randomInt];
        hiddenWord = str.toCharArray();
    }

    // MODIFIES: this
    // EFFECTS: creates a "blank" word according to the hiddenWord
    public void underscoreWord() {
        wordSoFar = new char[hiddenWord.length];
        for (int i = 0; i < wordSoFar.length; i++) {
            wordSoFar[i] = '_';
        }
    }

    // MODIFIES: this
    // EFFECTS: converts character array to a string
    public String charArrayToString(char[] c) {
        String str = String.valueOf(c);
        return str;
    }

    // EFFECTS: produce true if character is found in array
    public boolean charInCharArray(char c, char[] array) {
        for (char ch : array) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: replaces underscores in wordSoFar with appropriate letter from hiddenWord
    public void replaceUnderscores(char guess) {
        for (int i = 0; i < hiddenWord.length; i++) {
            if (guess == hiddenWord[i]) {
                wordSoFar[i] = hiddenWord[i];
            }
        }
    }

    // EFFECTS: prints a greeting message
    public void greetPlayer() {
        System.out.println("==================================");
        System.out.println("|            HANGMAN             |");
        System.out.println("==================================");
    }

    // EFFECTS: prints a game over message
    public void gameOver() {
        System.out.println("==================================");
        System.out.println("|           GAME OVER            |");
        System.out.println("==================================");
    }

    // EFFECTS: prints a congratulatory message
    public void gameWin() {
        System.out.println("==================================");
        System.out.println("|       CONGRATS YOU WON!        |");
        System.out.println("==================================");
    }

    public void game() {
        randomWord();
        underscoreWord();
        greetPlayer();

        while (!(lives == 0)) {
            System.out.println("Guess a Letter:");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            char guess = input.charAt(0);

            if (charInCharArray(guess, hiddenWord)) {
                replaceUnderscores(guess);
            } else {
                lives--;
            }
            System.out.println("Remaining Lives: " + lives);
            System.out.println(charArrayToString(wordSoFar));
        }
        gameOver();
    }
}





