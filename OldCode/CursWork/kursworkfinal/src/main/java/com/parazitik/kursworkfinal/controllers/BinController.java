package com.parazitik.kursworkfinal.controllers;

import com.parazitik.kursworkfinal.entity.BinEntity;
import com.parazitik.kursworkfinal.entity.ProductsEntity;
import com.parazitik.kursworkfinal.entity.UserEntity;
import com.parazitik.kursworkfinal.repository.BinRepository;
import com.parazitik.kursworkfinal.repository.ProductsRepository;
import com.parazitik.kursworkfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bin")
public class BinController {

    @Autowired
    BinRepository binrepo;

    @Autowired
    ProductsRepository productrepo;

    @Autowired
    UserRepository userepo;

    @GetMapping
    BinEntity ShowOne(@RequestParam Long UserId){
        return binrepo.findBinEntityByUserIdId(UserId);
    }

    @PostMapping
    public String AddInfo(@RequestParam Long userid, @RequestBody BinEntity bin){
        if(!(binrepo.findBinEntityByUserIdId(userid)== null)){
            return "";
        }else {
            UserEntity userEntity = userepo.findById(userid).get();
            bin.setUserId(userEntity);
            binrepo.save(bin);
            return "Всё ок";
        }
    }

    @PutMapping
    public String AddProducts(@RequestParam Long userId, @RequestParam Long productid){
        BinEntity entity = binrepo.findBinEntityByUserIdId(userId);
        ProductsEntity productsEntity = productrepo.findById(productid).get();
        entity.product(productsEntity);
        binrepo.save(entity);

        return "Всё ок";
    }

    @DeleteMapping
    public String deleteFromBin(@RequestParam Long userId, @RequestParam Long productid, @RequestParam Integer type){
        BinEntity bin = binrepo.findBinEntityByUserIdId(userId);

        if(type == 1){
            bin.deleteProduct(productrepo.findById(productid).get());
            binrepo.save(bin);
        }else if(type == 2){
            binrepo.delete(bin);
        }

        return "Успешно удалено";
    }
}
