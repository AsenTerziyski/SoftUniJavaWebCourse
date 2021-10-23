package com.example.myexamprep.repository;

import com.example.myexamprep.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("select u from UserEntity u order by u.orders.size desc ")
    List<UserEntity> findAllUsersAndTheirOrdersNumber();

}
