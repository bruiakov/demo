package com.restaurant.dto;

import java.util.List;

import com.restaurant.entity.OrderPart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
	private Long id;
	private Long orderNumber;
	private List<OrderPart> orders;
}
