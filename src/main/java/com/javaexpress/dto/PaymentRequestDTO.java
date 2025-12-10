package com.javaexpress.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequestDTO {

	private Long orderId;
	private Long userId;
	private BigDecimal amount;
}
