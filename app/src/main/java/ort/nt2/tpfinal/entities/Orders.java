package ort.nt2.tpfinal.entities;

//import java.util.ArrayList;

public class Orders {

    private int client_id;
    private int seller_id;
    private int product_id;
    //private ArrayList<Orders_product> item;



    public Orders(int client_id, int seller_id, int product_id) {
        this.setClient_id(client_id);
        this.setSeller_id(seller_id);
        this.setProduct_id(product_id);
    }

    public int getClient_id() {
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    /*public ArrayList<Orders_product> getItem() {



        return item;
    }

    public void setItem(ArrayList<Orders_product> item) {
        this.item = item;
    }*/
}
