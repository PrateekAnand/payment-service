package com.javaexpress.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDTO {

	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	private List<OrderItemResponseDTO> items;
	
	@JsonIgnore
	private UserDto userDto;
}
