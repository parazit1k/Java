package client.controllers;

public class Product {

    private String name;
    private String company;
    private String type;
    private double price;
    private int count;
    private Long id;

    public Product(){
        this.name = "";
        this.price = 0;
        this.company = "";
        this.type = "";
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product(String name, String company, String type, double price, int count, Long id){
        this.name = name;
        this.company = company;
        this.type = type;
        this.price = price;
        this.count = count;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
