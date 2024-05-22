package com.restaurant.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.dto.OrderDTO;
import com.restaurant.entity.Meal;
import com.restaurant.fasade.OrderFasade;

@RestController
@RequestMapping(path = "api")
public class OrderController {
	
	@Autowired
	private OrderFasade orderFasade;

	@GetMapping(path = "/orders/{orderNumber}")
	public OrderDTO getOrder(@PathVariable(name = "orderNumber") Long orderNumber) {
		return orderFasade.getOrderByNumber(orderNumber);
	}

	@PostMapping(path = "/orders")
	@ResponseBody
	public OrderDTO addOrder(@RequestBody List<Meal> order) {
		return orderFasade.createOrder(order);
	}

	@PutMapping(path = "/orders/{id}")
	public OrderDTO updateOrder(@PathVariable(name = "id") Long id, @RequestBody List<Meal> order) {
		return orderFasade.updateOrder(id, order);
	}

	@DeleteMapping(path = "/orders")
	public void deleteOrder(Long id) {
		orderFasade.deleteOrder(id);
	}
}
