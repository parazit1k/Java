package client.controllers;

public class Brands {

    private String brandname;
    private Long id;

    public Brands(long id, String brandname) {
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
