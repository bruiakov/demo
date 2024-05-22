package com.restaurant.fasade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.OrderDTO;
import com.restaurant.entity.Meal;
import com.restaurant.entity.Order;
import com.restaurant.entity.OrderPart;
import com.restaurant.repository.OrderPartRepository;
import com.restaurant.repository.OrderRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class OrderFasade {

	public OrderRepository orderRepository;
	public OrderPartRepository orderPartRepository;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Autowired
	public void setOrderPartRepository(OrderPartRepository orderPartRepository) {
		this.orderPartRepository = orderPartRepository;
	}

	private OrderDTO transformToOrderDTO(@NotNull Order order) {
		return OrderDTO.builder().id(order.getId())
				.orderNumber(order.getOrderNumber())
				.orders(orderPartRepository.findByOrder(order)).build();
	}

	@Transactional
	public OrderDTO createOrder(List<Meal> meals) {
		if (meals == null || meals.isEmpty()) {
			throw new IllegalArgumentException();
		}
		var orderNumber = orderRepository.count();
		var order = new Order();
		order.setOrderNumber(orderNumber);
		var saveOrder = orderRepository.save(order);
		var orderParts = meals.stream().map((i) -> {
			var orderPart = new OrderPart();
			orderPart.setMeal(i);
			orderPart.setOrder(saveOrder);
			return orderPart;
		}).collect(Collectors.toList());
		orderPartRepository.saveAll(orderParts);
		return transformToOrderDTO(saveOrder);
 	}

	public OrderDTO getOrderByNumber(@NotNull Long orderNumber) {
		var ord = orderRepository.findByOrderNumber(orderNumber).stream().findFirst().orElseThrow();
		return transformToOrderDTO(ord);
	}

	public void deleteOrder(@NotNull Long id) {
		orderRepository.findById(id).ifPresent(i -> orderRepository.delete(i));
	}

	@Transactional
	public OrderDTO updateOrder(@NotNull Long id, @NotNull List<Meal> meals) {
		var ord = orderRepository.findById(id).orElseThrow();
		orderPartRepository.deleteAll(orderPartRepository.findByOrder(ord));
		meals.forEach(i -> {
			var orderPart = new OrderPart();
			orderPart.setMeal(i);
			orderPart.setOrder(ord);
			orderPartRepository.save(orderPart);
		});
		return transformToOrderDTO(ord);
	}
}
