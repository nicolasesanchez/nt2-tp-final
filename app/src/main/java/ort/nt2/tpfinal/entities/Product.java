package ort.nt2.tpfinal.entities;

public class Product {
    public String name;
    public long qty;

    public Product(String name, long initialQty) {
        this.name = name;
        qty = initialQty;
    }
}
