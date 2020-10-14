package model;

import java.util.List;
import java.util.ArrayList;

// Represents a collection of sneakers
public class Collection {

    public List<Sneaker> collection;

    public Collection() {
        collection = new ArrayList<Sneaker>();
    }

    // MODIFIES: this
    // EFFECTS: adds a sneaker to the collection
    public void addSneaker(Sneaker s) {
        collection.add(s);
    }

    // REQUIRES: i < collection.size() - 1
    // MODIFIES: this
    // EFFECTS: removes a sneaker from collection with given index
    public void removeSneaker(int i) {
        collection.remove(i);
    }

    // EFFECTS: sums the total retail value of the collection
    public double totalRetailValue() {
        double total = 0;
        for (Sneaker s : collection) {
            total += s.getRetailValue();
        }
        return total;
    }

    // EFFECTS: sums the total resell value of the collection
    public double totalResellValue() {
        double total = 0;
        for (Sneaker s : collection) {
            total += s.getResellValue();
        }
        return total;
    }
}
