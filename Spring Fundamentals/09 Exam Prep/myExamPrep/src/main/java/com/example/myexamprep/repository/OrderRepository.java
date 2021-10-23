package com.example.myexamprep.repository;

import com.example.myexamprep.model.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAll();

    @Query("select o from OrderEntity o order by o.price desc")
    List<OrderEntity> findAllOrderByPriceDesc();


}
