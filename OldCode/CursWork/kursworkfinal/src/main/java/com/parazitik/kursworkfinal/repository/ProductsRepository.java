package com.parazitik.kursworkfinal.repository;

import com.parazitik.kursworkfinal.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    List<ProductsEntity> findByBinsId(Long BinId);
}
