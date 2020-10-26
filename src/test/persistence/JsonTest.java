package persistence;

import model.Sneaker;

import static org.junit.jupiter.api.Assertions.assertEquals;

// modelled after persistence.JsonTest from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkSneaker(String brand, String model, String colourway, double size, double condition,
                               double retailValue, double resellValue, Sneaker s) {
        assertEquals(brand, s.getBrand());
        assertEquals(model, s.getModel());
        assertEquals(colourway, s.getColourway());
        assertEquals(size, s.getShoeSize());
        assertEquals(condition, s.getCondition());
        assertEquals(retailValue, s.getRetailValue());
        assertEquals(resellValue, s.getResellValue());
    }
}
