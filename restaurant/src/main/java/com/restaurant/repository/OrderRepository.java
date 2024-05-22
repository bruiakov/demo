package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByOrderNumber(Long orderNumber);
}
