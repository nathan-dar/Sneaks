package model;

import java.util.Random;

public class Hangman {

    private static final int STARTING_LIVES = 6;
    private static final String[] WORD_BANK = {"COMPUTER SCIENCE", "TRUST THE NATURAL RECURSION", "HANGMAN"};
    private static final char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public int lives = 6;
    char[] wordSoFar;
    char[] hiddenWord;
    char[] availableLetters;

    public Hangman() {
    }

    // EFFECTS: returns wordSoFar
    public char[] getWordSoFar() {
        return wordSoFar;
    }

    // EFFECTS: returns wordSoFar
    public char[] getHiddenWord() {
        return hiddenWord;
    }

    // EFFECTS: returns lives
    public int getLives() {
        return lives;
    }

    // REQUIRES: lives > 0
    // MODIFIES: this
    // EFFECTS: reduces lives by 1
    public void wrongGuess() {
        lives--;
    }

    // MODIFIES: this
    // EFFECTS: selects and returns a random word from the word bank
    public String randomWord() {
        Random r = new Random();
        int randomInt = r.nextInt(WORD_BANK.length);
        return WORD_BANK[randomInt];
    }

    // MODIFIES: this
    // EFFECTS: creates a "blank" char array from given char array
    public char[] underscoreWord(char[] word) {
        char[] blankWord = new char[word.length];
        for (int i = 0; i < word.length; i++) {
            blankWord[i] = '_'; // case where a letter appears
            if (word[i] == ' ') {
                blankWord[i] = ' '; // case where a space appears
            }
        }
        return blankWord;
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

    // EFFECTS: checks if word is complete (game is won)
    public boolean wordComplete() {
        return (charArrayToString(wordSoFar).equals(charArrayToString(hiddenWord)));
    }

    // MODIFIES: this
    // EFFECTS: resets and sets up hangman game
    public void setupGame() {
        hiddenWord = null;
        wordSoFar = null;
        lives = STARTING_LIVES;
        availableLetters = LETTERS;
        hiddenWord = randomWord().toCharArray();
        wordSoFar = underscoreWord(hiddenWord);
    }
}





