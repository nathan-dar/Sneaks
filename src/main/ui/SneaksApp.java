package ui;

import model.Collection;
import model.Sneaker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// modelled after ca.ubc.cpsc210.bank.ui.TellerApp
// modelled after ui.WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents the sneaks application
public class SneaksApp {

    private static final String JSON_STORE = "./data/collection.json";

    public Scanner scan;
    public Collection collection;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts the Sneaks application
    public SneaksApp() {
        runSneaks();
    }

    // EFFECTS: runs the Sneaks application
    public void runSneaks() {
        boolean keepGoing = true;
        scan = new Scanner(System.in);
        collection = new Collection("User's Collection");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        drawIntro();

        while (keepGoing) {
            drawMenu();
            String input = scan.next().toLowerCase();

            if (input.equals("q")) {
                keepGoing = false;
            } else {
                processInput(input);
            }
        }
        drawOutro();
    }

    // MODIFIES: this
    // EFFECTS: process input
    public void processInput(String input) {
        if (input.equals("a")) {
            doAddSneaker();
        } else if (input.equals("r") && !collectionEmpty()) {
            doRemoveSneaker();
        } else if (input.equals("c") && !collectionEmpty()) {
            doCollectionStats();
        } else if (input.equals("v") && !collectionEmpty()) {
            doViewCollection();
        } else if (input.equals("s")) {
            doSaveCollection();
        } else if (input.equals("l")) {
            doLoadCollection();
        } else if (collectionEmpty()) {
            System.out.println("Your collection is empty.");
        } else {
            System.out.println("Input is not recognized, try again.");
        }
    }

    // EFFECTS: displays menu of options to user
    public void drawMenu() {
        System.out.println("\no------------ MENU ------------o");
        System.out.println("|  a -> add a sneaker          |");
        System.out.println("|  r -> remove a sneaker       |");
        System.out.println("|  c -> collection statistics  |");
        System.out.println("|  v -> view your collection   |");
        System.out.println("|  s -> save your collection   |");
        System.out.println("|  l -> load your collection   |");
        System.out.println("|  q -> quit                   |");
        System.out.println("o------------------------------o");
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
        System.out.println("|   SNEAKS is closing...   |");
        System.out.println("============================");
    }

    // checks if the collection is empty, return true if empty, otherwise false
    public boolean collectionEmpty() {
        return (collection.collectionSize() == 0);
    }

    // MODIFIES: this
    // EFFECTS: asks the user to input the Sneakers information, then adds it to the collection
    public void doAddSneaker() {
        System.out.println("\nEnter the following information to add a Sneaker.\n");
        System.out.println("Brand:");
        String brand = askForString();
        System.out.println("Model:");
        String model = askForString();
        System.out.println("Colourway:");
        String colour = askForString();
        System.out.println("Size:");
        double size = askForDouble();
        System.out.println("Condition [X/10]:");
        double condition = askForDouble();
        System.out.println("Retail Value:");
        double retail = askForDouble();
        System.out.println("Resell Value:");
        double resell = askForDouble();
        Sneaker s = new Sneaker(brand, model, colour, size, condition, retail, resell);
        collection.addSneaker(s);
        System.out.println(s.getBrand() + " " + s.getModel() + " " + s.getColourway() + " has been added.");
    }

    // EFFECTS: gets a string input from user, returns string
    public String askForString() {
        Scanner strScan = new Scanner(System.in);
        return strScan.nextLine().toUpperCase();
    }

    // REQUIRES: user input must be of double type
    // EFFECTS: gets a double input from user, returns double
    public double askForDouble() {
        return scan.nextDouble();
    }

    // MODIFIES: this
    // EFFECTS: removes a chosen sneaker from the collection
    public void doRemoveSneaker() {
        int i = 1;

        for (Sneaker s : collection.getCollection()) {
            System.out.println(i + ".) " + s.getBrand() + " " + s.getModel() + " " + s.getColourway());
            i++;
        }
        System.out.println("Enter the number of the sneaker you want to remove:");
        int remove = scan.nextInt();
        Sneaker sn = collection.getSneaker(remove - 1);
        collection.removeSneaker(remove - 1);
        System.out.println(sn.getBrand() + " " + sn.getModel() + " " + sn.getColourway() + " has been removed.");
    }

    // EFFECTS: displays the collections statistics
    public void doCollectionStats() {
        System.out.println("Total sneakers in collection: " + collection.collectionSize());
        System.out.println("Total Retail Value: $" + collection.totalRetailValue());
        System.out.println("Total Resell Value: $" + collection.totalResellValue());
    }

    // EFFECTS: displays the entire sneaker collection
    public void doViewCollection() {
        for (Sneaker s : collection.getCollection()) {
            System.out.println("o-----------------------------------------o");
            System.out.println(s.getBrand() + " " + s.getModel() + " " + s.getColourway());
            System.out.println("        Size: " + s.getShoeSize());
            System.out.println("   Condition: " + s.getCondition() + "/10.0");
            System.out.println("Retail Value: $" + s.getRetailValue());
            System.out.println("Resell Value: $" + s.getResellValue());
        }
        System.out.println("o-----------------------------------------o");
    }

    // EFFECTS: saves the workroom to file
    private void doSaveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
            System.out.println("Saved " + collection.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void doLoadCollection() {
        try {
            collection = jsonReader.read();
            System.out.println("Loaded " + collection.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
