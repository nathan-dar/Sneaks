package model;

// Represents a sneaker with, brand, model, size, condition, retail value and resell value
public class Sneaker {

    public String brand;
    public String model;
    public String colourway;
    public double shoeSize;
    public double condition;
    public double retailValue;
    public double resellValue;

    public Sneaker(String brand, String model, String colour, double size, double condition, double retail, double resell) {
        this.brand = brand;
        this.model = model;
        this.colourway = colour;
        this.shoeSize = size;
        this.condition = condition;
        this.retailValue = retail;
        this.resellValue = resell;
    }

    // EFFECTS: returns retailValue
    public double getRetailValue() {
        return retailValue;
    }

    // EFFECTS: returns resellValue
    public double getResellValue() {
        return resellValue;
    }
}
