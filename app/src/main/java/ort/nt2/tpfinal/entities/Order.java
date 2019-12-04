package ort.nt2.tpfinal.entities;

public class Order {

    private int client_id;
    private int seller_id;

    public int getId() {
        return id;
    }

    private int id;

    public Order(int id, int client_id, int seller_id) {
        this.id = id;
        this.setClient_id(client_id);
        this.setSeller_id(seller_id);
    }

    public int getClientId() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }


    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

}
