package client.controllers;

import java.util.List;

public class UsersOrders {

    private String username;
    private Long orderid;
    private List<String> products;

    public UsersOrders(String username, Long orderid, List<String> products) {
        this.username = username;
        this.orderid = orderid;
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
