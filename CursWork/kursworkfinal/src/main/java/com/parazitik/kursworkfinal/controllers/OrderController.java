package com.parazitik.kursworkfinal.controllers;

import com.parazitik.kursworkfinal.entity.OrderEntity;
import com.parazitik.kursworkfinal.repository.OrderRepository;
import com.parazitik.kursworkfinal.repository.ProductsRepository;
import com.parazitik.kursworkfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    OrderRepository orderepo;

    @Autowired
    UserRepository userepo;

    @Autowired
    ProductsRepository productrepo;

    @GetMapping
    public List<OrderEntity> getAll(){
        return orderepo.findAll();
    }

    @PostMapping
    public OrderEntity addOrder(@RequestParam Long userid, @RequestBody OrderEntity entity){
        entity.setUserEntity(userepo.findById(userid).get());
        return orderepo.save(entity);
    }

    @PutMapping
    public String addProducts(@RequestParam Long productIds, @RequestParam Long orderId){
        OrderEntity entity = orderepo.findById(orderId).get();
        entity.add(productrepo.findById(productIds).get());
        orderepo.save(entity);
        return "Добавил";
    }

    @DeleteMapping
    public String deleteOrder(@RequestParam Long orderId){
        orderepo.deleteById(orderId);
        return "Удалил";
    }

}
