package ui;

import model.Collection;
import model.Sneaker;

import java.util.Scanner;

// Sneaks application
public class SneaksApp {

    public Scanner scan;
    public Collection collection;

    // EFFECTS: starts the Sneaks application
    public SneaksApp() {
        runSneaks();
    }

    // EFFECTS: runs the Sneaks application
    public void runSneaks() {
        boolean keepGoing = true;
        scan = new Scanner(System.in);
        drawIntro();

        while (keepGoing) {
            drawHome();
            String input = this.scan.next().toLowerCase();

            if (input.equals("q")) {
                keepGoing = false;
            } else {
                processInput(input);
            }
        }
        drawOutro();
    }

    // MODIFIES: this
    // EFFECTS: processCommand
    public void processInput(String input) {
        if (input.equals("a")) {
            doAddSneaker();
        } else if (input.equals("r")) {
            doRemoveSneaker();
        } else if (input.equals("c")) {
            doCollectionStats();
        } else if (input.equals("v")) {
            doViewCollection();
        } else {
            System.out.println("Input is not recognized, try again.");
        }
    }

    // EFFECTS: displays menu of options to user
    public void drawHome() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a sneaker");
        System.out.println("\tr -> remove a sneaker");
        System.out.println("\tc -> vew your collection statistics");
        System.out.println("\tv -> view your sneakers");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays introduction message
    public void drawIntro() {
        System.out.println("============================");
        System.out.println("|          SNEAKS          |");
        System.out.println("============================");

    }

    // EFFECTS: displays outro message
    public void drawOutro() {
        System.out.println("============================");
        System.out.println("|         GoodBye!         |");
        System.out.println("============================");
    }

    // MODIFIES: this
    // EFFECTS: asks the user to input the Sneakers information, then adds it to the collection
    public void doAddSneaker() {
        System.out.println("Brand:");
        String brand = askForString();
        System.out.println("Model:");
        String model = askForString();
        System.out.println("Colourway:");
        String colour = askForString();
        System.out.println("Size:");
        double size = askForDouble();
        System.out.println("Condition:");
        double condition = askForDouble();
        System.out.println("Retail Value:");
        double retail = askForDouble();
        System.out.println("Resell Value:");
        double resell = askForDouble();
        Sneaker s = new Sneaker(brand, model, colour, size, condition, retail, resell);
        collection.addSneaker(s);
    }

    // EFFECTS: gets a string input from user, returns string
    public String askForString() {
        return this.scan.next().toUpperCase();
    }

    // EFFECTS: gets a double input from user, returns double
    public double askForDouble() {
        return this.scan.nextDouble();
    }

    // EFFECTS: removes a chosen sneaker from the collection
    public void doRemoveSneaker() {
        // stub
    }

    // EFFECTS: displays the collections statistics
    public void doCollectionStats() {
        // stub
    }

    // EFFECTS: displays the entire sneaker collection
    public void doViewCollection() {
        // stub
    }



}
