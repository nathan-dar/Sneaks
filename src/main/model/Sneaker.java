package model;

import org.json.JSONObject;
import persistence.Writable;

// toJson method modelled from model.Thingy from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a sneaker with, brand, model, colourway, size, condition, retail value and resell value
public class Sneaker implements Writable {

    private String brand;
    private String model;
    private String colourway;
    private double shoeSize;
    private double condition;
    private double retailValue;
    private double resellValue;

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("brand", brand);
        json.put("model", model);
        json.put("colourway", colourway);
        json.put("size", shoeSize);
        json.put("condition", condition);
        json.put("retailValue", retailValue);
        json.put("resellValue", resellValue);
        return json;
    }

}
