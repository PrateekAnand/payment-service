package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.PaymentRequestDTO;
import com.javaexpress.dto.PaymentResponseDTO;
import com.javaexpress.service.IPaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payments")
@Slf4j
public class PaymentController {

	@Autowired
	private IPaymentService paymentService;
	
	@PostMapping
	public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO request) {
		log.info("PaymentController makePayment {}",request.getOrderId());
		return paymentService.processPayment(request);
	}
}
