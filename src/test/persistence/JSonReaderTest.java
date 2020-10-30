package persistence;

import model.Collection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// modelled after persistence.JsonReaderTest from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JSonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Collection c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCollection.json");
        try {
            Collection c = reader.read();
            assertEquals("Empty Collection", c.getName());
            assertEquals(0, c.collectionSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCollection.json");
        try {
            Collection c = reader.read();
            assertEquals("General Collection", c.getName());
            assertEquals(2, c.collectionSize());
            checkSneaker("NIKE", "AIRFORCE", "WHITE", 10, 10, 100, 100, c.getSneaker(0));
            checkSneaker("ADIDAS", "YEEZY", "TURTLE DOVE", 9, 9, 300, 1000, c.getSneaker(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
