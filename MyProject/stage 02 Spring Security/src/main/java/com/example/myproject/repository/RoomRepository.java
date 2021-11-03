package com.example.myproject.repository;

import com.example.myproject.model.entities.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomTypeEntity, Long> {
}
