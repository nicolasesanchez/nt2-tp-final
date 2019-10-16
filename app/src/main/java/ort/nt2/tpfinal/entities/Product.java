package ort.nt2.tpfinal.entities;

public class Product {
    public String name;
    public float price;

    public Product(String name, float initialQty) {
        this.name = name;
        price = initialQty;
    }
}
