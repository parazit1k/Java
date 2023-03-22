package com.example.test.repo;

import com.example.test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByRowId(Long rowid);
}
