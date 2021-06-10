package com.parazitik.kursworkfinal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToMany
    @JoinTable(
            name = "OrderProduct",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<ProductsEntity> productsOrder = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<ProductsEntity> getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(List<ProductsEntity> productsOrder) {
        this.productsOrder = productsOrder;
    }

    public void add(ProductsEntity product) {
        productsOrder.add(product);
    }
}
