package com.parazitik.kursworkfinal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "BinProduct",
            joinColumns = @JoinColumn(name = "bin_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductsEntity> products = new ArrayList<>();

    public List<ProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsEntity> products) {
        this.products = products;
    }

    public BinEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public void product(ProductsEntity productsEntity) {
        products.add(productsEntity);
    }

    public void deleteProduct(ProductsEntity productsEntity) {
        products.remove(productsEntity);
    }
}
