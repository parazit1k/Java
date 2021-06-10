package com.parazitik.kursworkfinal.controllers;

import com.parazitik.kursworkfinal.entity.BrandEntity;
import com.parazitik.kursworkfinal.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandRepository brandrepo;

    @GetMapping
    public Iterable<BrandEntity> GetAll(){
        return brandrepo.findAll();

    }

    @PostMapping
    public String createBrand(@RequestBody BrandEntity brand){
        brandrepo.save(brand);
        return("Всё ок!");
    }

    @PutMapping
    public String editBrand(@RequestBody BrandEntity brand){
        brandrepo.save(brand);
        return "Успешно";
    }
}
