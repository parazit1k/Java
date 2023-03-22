package com.example.test.repo;

import com.example.test.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {

    Status findStatusByRowId(Long id);
}
