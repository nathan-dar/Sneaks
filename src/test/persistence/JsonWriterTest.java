package persistence;

import model.Sneaker;
import model.Collection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// modelled after persistence.JsonWriterTest from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Collection c = new Collection("Test Collection");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Collection c = new Collection("Empty Collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            c = reader.read();
            assertEquals("Empty Collection", c.getName());
            assertEquals(0, c.collectionSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Collection c = new Collection("General Collection");
            c.addSneaker(new Sneaker("NIKE", "AIRFORCE", "WHITE", 10, 10, 100, 100));
            c.addSneaker(new Sneaker("ADIDAS", "YEEZY", "TURTLE DOVE", 9, 9, 300, 1000));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            c = reader.read();
            assertEquals("General Collection", c.getName());
            assertEquals(2, c.collectionSize());
            checkSneaker("NIKE", "AIRFORCE", "WHITE", 10, 10, 100, 100, c.getSneaker(0));
            checkSneaker("ADIDAS", "YEEZY", "TURTLE DOVE", 9, 9, 300, 1000, c.getSneaker(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}