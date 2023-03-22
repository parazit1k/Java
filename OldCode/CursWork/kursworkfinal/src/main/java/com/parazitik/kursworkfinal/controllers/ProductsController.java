package com.parazitik.kursworkfinal.controllers;

import com.parazitik.kursworkfinal.entity.BinEntity;
import com.parazitik.kursworkfinal.entity.BrandEntity;
import com.parazitik.kursworkfinal.entity.ProductsEntity;
import com.parazitik.kursworkfinal.repository.BinRepository;
import com.parazitik.kursworkfinal.repository.BrandRepository;
import com.parazitik.kursworkfinal.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductsRepository productsrepo;

    @Autowired
    BrandRepository brandrepo;

    @Autowired
    BinRepository binrepo;

    @GetMapping
    List<ProductsEntity> getAll(){
        return productsrepo.findAll();
    }

    @PostMapping
    public ProductsEntity createProduct(@RequestBody ProductsEntity product, @RequestParam String brandname){
        BrandEntity brand = brandrepo.findBrandEntityByBrandname(brandname);
        product.setBrand(brand);
        return productsrepo.save(product);
    }

    @PutMapping
    public String editProduct(@RequestBody ProductsEntity product){

        if(!product.getName().isEmpty()){
            product.setName(product.getName());
        }
        if(!product.getPrice().equals(productsrepo.findById(product.getId()).get().getPrice())){
            product.setPrice(product.getPrice());
        }
        if(!product.getType().isEmpty()){
            product.setType(product.getType());
        }
        if(!product.getCount().equals(productsrepo.findById(product.getId()).get().getCount())){
            product.setCount(product.getCount());
        }
        product.setBrand(brandrepo.findBrandEntityByBrandname(product.getBrand().getBrandname()));
        productsrepo.save(product);
        return "Данные изменены";
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam Long productId){
        ProductsEntity product = productsrepo.findById(productId).get();
        List<BinEntity> bin = binrepo.findBinEntitiesByProducts(product);
        for(BinEntity i : bin){
            i.deleteProduct(product);
        }
        productsrepo.delete(product);
        return "Продукт удалён";
    }
}
