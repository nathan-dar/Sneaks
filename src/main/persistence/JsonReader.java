package persistence;

import model.Collection;
import model.Sneaker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// modelled after persistence.Reader from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Collection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses collection from JSON object and returns it
    private Collection parseCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Collection c = new Collection(name);
        addSneakers(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses sneakers from JSON object and adds them to collection
    private void addSneakers(Collection c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sneakers");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addSneaker(c, nextThingy);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses sneaker from JSON object and adds it to collection
    private void addSneaker(Collection c, JSONObject jsonObject) {
        String brand = jsonObject.getString("brand");
        String model = jsonObject.getString("model");
        String colourway = jsonObject.getString("colourway");
        Double size = jsonObject.getDouble("size");
        Double condition = jsonObject.getDouble("condition");
        Double retailValue = jsonObject.getDouble("retailValue");
        Double resellValue = jsonObject.getDouble("resellValue");
        Sneaker s = new Sneaker(brand, model, colourway, size, condition, retailValue, resellValue);
        c.addSneaker(s);
    }
}
