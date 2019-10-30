package ort.nt2.tpfinal.entities;

public class Product {
    private String name;
    private float price;
    private int stock;

    public Product(String name, float price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }
}
