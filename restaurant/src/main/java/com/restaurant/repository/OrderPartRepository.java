package com.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.entity.Order;
import com.restaurant.entity.OrderPart;

public interface OrderPartRepository extends JpaRepository<OrderPart, Long> {

	List<OrderPart> findByOrder(Order order);
}
