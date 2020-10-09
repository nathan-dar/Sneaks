package model;

import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public String[] wordBank = {"TESTING"};
    char[] wordSoFar;
    char[] hiddenWord;
    char[] availableLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
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
        return String.valueOf(c);
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

    // MODIFIES: this
    // EFFECTS: asks a user to "guess a letter", then returns the guess as a character
    public char guessALetter() {
        System.out.println("Guess a Letter:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        char guess = input.charAt(0);
        return guess;
    }

    // EFFECTS: checks if word is complete (game is won)
    public boolean wordComplete() {
        return (charArrayToString(wordSoFar).equals(charArrayToString(hiddenWord)));
    }

    // MODIFIES:  this
    // EFFECTS: runs the hangman game
    public void game() {
        Ascii ascii = new Ascii();
        randomWord(); // randomly selects word from wordBank
        underscoreWord(); // creates a "blank" (underscored) version of the word
        ascii.gameIntro();
        System.out.println(charArrayToString(wordSoFar));

        while (!(lives == 0)) {
            char guess = guessALetter();

            if (charInCharArray(guess, hiddenWord)) {
                replaceUnderscores(guess);
                if (wordComplete()) {
                    ascii.gameWin();
                    break;
                }
            } else {
                lives--;
            }
            System.out.println("Remaining Lives: " + lives);
            ascii.printHangmanAscii(lives);
            System.out.println(charArrayToString(wordSoFar));
        }
        ascii.printHangmanAscii(lives);
        ascii.gameOver();
    }
}





