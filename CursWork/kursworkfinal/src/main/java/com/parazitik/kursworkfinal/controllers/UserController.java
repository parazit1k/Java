package com.parazitik.kursworkfinal.controllers;

import com.parazitik.kursworkfinal.entity.UserEntity;
import com.parazitik.kursworkfinal.repository.UserRepository;
import com.parazitik.kursworkfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userepo;

    @Autowired
    private UserService userserv;

    UserEntity userentity = new UserEntity();

    @GetMapping
    public List<UserEntity> GetAll(){
        return userepo.findAll();
    }



    @PostMapping
    public String PostInfo(@RequestBody UserEntity user){
        try {
            if(userserv.CheckForNickname(user)) {
                if(userserv.CheckForEmail(user)){
                    userepo.save(user);
                }else{
                    return "Такой E-mail уже есть";
                }
            }else{
                return "Такой логин уже есть";
            }
        }catch (Exception e){
            System.out.println(e);
            return "Нужно ввести Username и Password";
        }
        return "Вы успешно зарегистрировались!";
    }

    @PutMapping
    public String ChangeInfo(@RequestBody UserEntity user){
        UserEntity entity = userepo.findById(user.getId()).get();
        if(!user.getUsername().isEmpty()){
            entity.setUsername(user.getUsername());
        }else if(!user.getLastname().isEmpty()) {
            entity.setLastname(user.getLastname());
        }else if(!user.getAdress().isEmpty()) {
            entity.setAdress(user.getAdress());
        }else if(!user.getEmail().isEmpty()) {
            entity.setEmail(user.getEmail());
        }else if(!user.getPassword().isEmpty()) {
            entity.setPassword(user.getPassword());
        }
        userepo.save(entity);
        return "Информация изменена";
    }

    @DeleteMapping
    void DeleteId(@RequestParam Long id){
        userepo.deleteById(id);
    }
}