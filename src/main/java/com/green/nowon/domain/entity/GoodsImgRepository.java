package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsImgRepository extends JpaRepository<GoodsImg, Long>{


    Optional<GoodsImg> findByUrlAndOrgName(String path, String fileName);

    void deleteByUrlAndNewName(String path, String fileName);
}
