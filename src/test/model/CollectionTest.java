package model;

import exceptions.EmptyCollectionException;
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
        try {
            assertEquals(0, c.totalRetailValue());
            fail("Supposed to throw EmptyCollectionException");
        } catch (EmptyCollectionException e) {
            //pass
        }
    }

    @Test
    public void totalRetailValueTestTwoInCollection() {
        try {
            c.addSneaker(s1);
            assertEquals(1, c.collectionSize());
            assertEquals(3, c.totalRetailValue());
            c.addSneaker(s2);
            assertEquals(2, c.collectionSize());
            assertEquals(10, c.totalRetailValue());
        } catch (EmptyCollectionException e) {
            fail("Not supposed to throw EmptyCollectionException");
        }
    }

    @Test
    public void totalResellValueTestEmptyCollection() {
        try {
            assertEquals(0, c.totalResellValue());
            fail("Supposed to throw EmptyCollectionException");
        } catch (EmptyCollectionException e) {
            //pass
        }
    }

    @Test
    public void totalResellValueTestTwoInCollection() {
        try {
        c.addSneaker(s1);
        assertEquals(1, c.collectionSize());
        assertEquals(4, c.totalResellValue());
        c.addSneaker(s2);
        assertEquals(2, c.collectionSize());
        assertEquals(12, c.totalResellValue());
        } catch (EmptyCollectionException e) {
            fail("Not supposed to throw EmptyCollectionException");
        }
    }

    @Test
    public void addSneakerThenRemoveSneakerTest() {
        try {
        c.addSneaker(s1);
        assertEquals(1, c.collectionSize());
        assertEquals(s1, c.getSneaker(0));
        c.removeSneaker(0);
        assertEquals(0, c.collectionSize());
        assertEquals(0, c.getCollection().size());
        } catch (EmptyCollectionException e) {
            fail("Not supposed to throw EmptyCollectionException");
        }
    }

    @Test
    public void removeSneakerWithEmptyCollection() {
        try {
            c.removeSneaker(1);
        } catch (EmptyCollectionException e) {
            //pass
        }
    }

}
