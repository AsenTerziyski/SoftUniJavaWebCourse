package com.example.pathfinder.repository;

import com.example.pathfinder.model.entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository <PictureEntity, Long>{
    @Query("select p.url from PictureEntity p")
    List<String> findAllPictureUrls();
}
