package com.javaexpress.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponseDTO {

	private Long paymentId;
	private Long orderId;
	private BigDecimal amount;
	private String status;
	private LocalDateTime paymentTime;
	private UserDto userDto;
}
