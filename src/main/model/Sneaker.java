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

    public Sneaker(String brand, String model, String colour, double size, double cond, double retail, double resell) {
        this.brand = brand;
        this.model = model;
        this.colourway = colour;
        this.shoeSize = size;
        this.condition = cond;
        this.retailValue = retail;
        this.resellValue = resell;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColourway() {
        return colourway;
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public double getCondition() {
        return condition;
    }

    public double getRetailValue() {
        return retailValue;
    }

    public double getResellValue() {
        return resellValue;
    }

}
