package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private static final int LIVES = 5;

    public List<Character> guessedLetters = new ArrayList<Character>();
    public List<Character> availableLetters = new ArrayList<Character>();

    public String hiddenWord = "TESTING";
    int attempts = 0;


    public Hangman() {
    }

    // EFFECTS: print a greeting message
    public void greetPlayer() {
        System.out.println("==================================");
        System.out.println("|            HANGMAN             |");
        System.out.println("==================================");
    }

    // EFFECTS: print a game over message
    public void gameOver() {
        System.out.println("==================================");
        System.out.println("|           GAME OVER            |");
        System.out.println("==================================");
    }

    public void game() {
        greetPlayer();
        while (attempts < LIVES) {
            System.out.println("Guess a Letter:");
            Scanner scan = new Scanner(System.in);
            String guess = scan.nextLine();
            if (guess)
        }
        gameOver();
    }
}




