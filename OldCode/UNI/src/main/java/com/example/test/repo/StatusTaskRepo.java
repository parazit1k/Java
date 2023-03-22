package com.example.test.repo;

import com.example.test.models.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTaskRepo extends JpaRepository<StatusTask, Long> {
}
