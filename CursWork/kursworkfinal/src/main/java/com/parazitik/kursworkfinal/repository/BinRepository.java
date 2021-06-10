package com.parazitik.kursworkfinal.repository;

import com.parazitik.kursworkfinal.entity.BinEntity;
import com.parazitik.kursworkfinal.entity.ProductsEntity;
import com.parazitik.kursworkfinal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BinRepository extends JpaRepository<BinEntity, Long> {
    BinEntity findBinEntityByUserIdId(Long userId);
    List<BinEntity> findBinEntitiesByProducts(ProductsEntity product);
}
