package model;

public class Ascii {

    public Ascii() {
    }
    // EFFECTS: prints a introduction message
    public void gameIntro() {
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

    // EFFECTS: prints hangman Ascii art based on remaining lives
    public void printHangmanAscii(int lives) {
        if (lives == 5) {
            hangmanAscii5Lives();
        } else if (lives == 4) {
            hangmanAscii4Lives();
        } else if (lives == 3) {
            hangmanAscii3Lives();
        } else if (lives == 2) {
            hangmanAscii2Lives();
        } else if (lives == 1) {
            hangmanAscii1Lives();
        } else if (lives == 0) {
            hangmanAscii0Lives();
        }
    }

    // EFFECTS: print hangman ascii with 5 lives left
    public void hangmanAscii5Lives() {
        System.out.println(" +----+    ");
        System.out.println(" |    |    ");
        System.out.println(" |         ");
        System.out.println(" |         ");
        System.out.println(" |         ");
        System.out.println(" |         ");
        System.out.println("===========");
    }

    // EFFECTS: print hangman ascii with 4 lives left
    public void hangmanAscii4Lives() {
        System.out.println(" +----+    ");
        System.out.println(" |    |    ");
        System.out.println(" |    0    ");
        System.out.println(" |    |    ");
        System.out.println(" |         ");
        System.out.println(" |         ");
        System.out.println("===========");
    }

    // EFFECTS: print hangman ascii with 3 lives left
    public void hangmanAscii3Lives() {
        System.out.println(" +----+    ");
        System.out.println(" |    |    ");
        System.out.println(" |    0    ");
        System.out.println(" |    |\\   ");
        System.out.println(" |         ");
        System.out.println(" |         ");
        System.out.println("===========");
    }

    // EFFECTS: print hangman ascii with 2 lives left
    public void hangmanAscii2Lives() {
        System.out.println(" +----+    ");
        System.out.println(" |    |    ");
        System.out.println(" |    0    ");
        System.out.println(" |   /|\\   ");
        System.out.println(" |         ");
        System.out.println(" |         ");
        System.out.println("===========");
    }

    // EFFECTS: print hangman ascii with 1 lives left
    public void hangmanAscii1Lives() {
        System.out.println(" +----+    ");
        System.out.println(" |    |    ");
        System.out.println(" |    0    ");
        System.out.println(" |   /|\\   ");
        System.out.println(" |     \\   ");
        System.out.println(" |         ");
        System.out.println("===========");
    }

    // EFFECTS: print hangman ascii with 0 lives
    public void hangmanAscii0Lives() {
        System.out.println(" +----+    ");
        System.out.println(" |    |    ");
        System.out.println(" |    0    ");
        System.out.println(" |   /|\\   ");
        System.out.println(" |   / \\   ");
        System.out.println(" |         ");
        System.out.println("===========");
    }
}
