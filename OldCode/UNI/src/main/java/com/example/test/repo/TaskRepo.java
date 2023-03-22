package com.example.test.repo;

import com.example.test.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

    Task findTaskByTitle(String title);
    Task findTaskByRowId(Long rowId);
}
