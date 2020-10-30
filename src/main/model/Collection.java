package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// toJson and sneakersToJson methods modelled from model.WorkRoom from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a collection of sneakers
public class Collection implements Writable {

    private List<Sneaker> collection;
    private String name;

    public Collection(String name) {
        this.name = name;
        collection = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a sneaker to the collection
    public void addSneaker(Sneaker s) {
        collection.add(s);
    }

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

    // EFFECTS: returns collection size
    public int collectionSize() {
        return collection.size();
    }

    public List<Sneaker> getCollection() {
        return collection;
    }

    // REQUIRES: collection.size() > 0
    public Sneaker getSneaker(int i) {
        return collection.get(i);
    }

    // EFFECTS: returns the collection's name
    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sneakers", sneakersToJson());
        return json;
    }

    // EFFECTS: returns things in this collection as a JSON array
    private JSONArray sneakersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Sneaker s : collection) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
