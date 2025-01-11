package com.petproject.orderservice.repository;

import com.petproject.orderservice.model.Order;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
     SELECT o FROM Order o
     WHERE o.orderDate BETWEEN :startDate AND :endDate
     """)
    List<Order> getOrderByDateBetween(LocalDate startDate, LocalDate endDate);
}
