package client.controllers;

import java.util.List;

public class Orders {

    private Long orderid;
    private List<String> products;

    public Orders(Long orderid, List<String> products) {
        this.orderid = orderid;
        this.products = products;
    }


    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
