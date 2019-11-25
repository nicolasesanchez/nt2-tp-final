package ort.nt2.tpfinal.entities;

public class Product {
    private int id;
    private String name;
    private float price;
    private int stock;

    public Product(int id, String name, float price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

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

    public int getId() {
        return this.id;
    }
}
