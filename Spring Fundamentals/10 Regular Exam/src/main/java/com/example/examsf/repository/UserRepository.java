package com.example.examsf.repository;

import com.example.examsf.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameAndEmail(String username, String email);
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("select u from UserEntity u where u.ship.size >0 ")
    List<UserEntity> findAllUsersWhoOwnShips();
}
