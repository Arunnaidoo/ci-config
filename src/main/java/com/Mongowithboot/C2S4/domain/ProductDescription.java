package com.Mongowithboot.C2S4.domain;

public class ProductDescription {
    private int stock;
    private String brand;
    private float weight;
    private String dimension;

    public ProductDescription() {
    }

    public ProductDescription(int stock, String brand, float weight, String dimension) {
        this.stock = stock;
        this.brand = brand;
        this.weight = weight;
        this.dimension = dimension;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "ProductDescription{" +
                "stock=" + stock +
                ", brand='" + brand + '\'' +
                ", weight=" + weight +
                ", dimension='" + dimension + '\'' +
                '}';
    }
}
