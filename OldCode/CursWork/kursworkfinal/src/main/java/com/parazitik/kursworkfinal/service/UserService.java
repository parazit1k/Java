package com.parazitik.kursworkfinal.service;

import com.parazitik.kursworkfinal.entity.UserEntity;
import com.parazitik.kursworkfinal.model.UserModel;
import com.parazitik.kursworkfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userepo;

    public boolean CheckForNickname(UserEntity User){
        return userepo.findByUsername(User.getUsername()) == null;
    }

    public boolean CheckForEmail(UserEntity User){
        return userepo.findByEmail(User.getEmail()) == null;
    }



}
