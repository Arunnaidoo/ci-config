package com.Mongowithboot.C2S4.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Id
    private int pID;
    private String name;
    private int price;
    private ProductDescription productDescription;

    public Product() {
    }

    public Product(int pID, String name, int price, ProductDescription productDescription) {
        this.pID = pID;
        this.name = name;
        this.price = price;
        this.productDescription = productDescription;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pID=" + pID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productDescription=" + productDescription +
                '}';
    }
}
