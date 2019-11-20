package ort.nt2.tpfinal.entities;

public class Orders_product {
    private int quantity;
    private int orders_id;
    private int product_id;

    public Orders_product(int quantity, int orders_id, int product_id) {
        this.setOrders_id(orders_id);
        this.setProduct_id(product_id);
        this.setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
