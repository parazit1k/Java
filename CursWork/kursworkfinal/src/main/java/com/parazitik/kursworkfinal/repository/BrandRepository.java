package com.parazitik.kursworkfinal.repository;

import com.parazitik.kursworkfinal.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    BrandEntity findBrandEntityByBrandname(String brandname);
}
