package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Collection
public class CollectionTest {

    Collection c;
    Sneaker s1;
    Sneaker s2;

    @BeforeEach
    public void setup() {
        c = new Collection("Test Collection");
        s1 = new Sneaker("A", "B", "C", 1, 2, 3, 4);
        s2 = new Sneaker("D", "E", "F", 5, 6, 7, 8);
    }

    @Test
    public void totalRetailValueTestEmptyCollection() {
        assertEquals(0, c.collectionSize());
        assertEquals(0, c.totalRetailValue());
    }

    @Test
    public void totalRetailValueTestTwoInCollection() {
        c.addSneaker(s1);
        assertEquals(1, c.collectionSize());
        assertEquals(3, c.totalRetailValue());
        c.addSneaker(s2);
        assertEquals(2, c.collectionSize());
        assertEquals(10, c.totalRetailValue());
    }

    @Test
    public void totalResellValueTestEmptyCollection() {
        assertEquals(0, c.collectionSize());
        assertEquals(0, c.totalResellValue());
    }

    @Test
    public void totalResellValueTestTwoInCollection() {
        c.addSneaker(s1);
        assertEquals(1, c.collectionSize());
        assertEquals(4, c.totalResellValue());
        c.addSneaker(s2);
        assertEquals(2, c.collectionSize());
        assertEquals(12, c.totalResellValue());
    }

    @Test
    public void addSneakerThenRemoveSneakerTest() {
        c.addSneaker(s1);
        assertEquals(1, c.collectionSize());
        assertEquals(s1, c.getSneaker(0));
        c.removeSneaker(0);
        assertEquals(0, c.collectionSize());
        assertEquals(0, c.getCollection().size());
    }

}
