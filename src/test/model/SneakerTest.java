package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SneakerTest {

    @Test
    void gettersTests() {
        Sneaker s = new Sneaker("NIKE", "AIRFORCE", "WHITE", 10, 9.5, 119.99, 99.99);
        assertEquals("NIKE", s.getBrand());
        assertEquals("AIRFORCE", s.getModel());
        assertEquals("WHITE", s.getColourway());
        assertEquals(10, s.getShoeSize());
        assertEquals(9.5, s.getCondition());
        assertEquals(119.99, s.getRetailValue());
        assertEquals(99.99, s.getResellValue());
    }

}
