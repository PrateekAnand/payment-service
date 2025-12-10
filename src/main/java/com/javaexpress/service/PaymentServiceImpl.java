package com.javaexpress.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.OrderResponseDTO;
import com.javaexpress.dto.PaymentRequestDTO;
import com.javaexpress.dto.PaymentResponseDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.feignclients.OrderFeignClient;
import com.javaexpress.feignclients.UserFeignClient;
import com.javaexpress.models.Payment;
import com.javaexpress.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderFeignClient orderFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private UserIntegrationService userIntegrationService; // Inject new Service
	

	@Override
	public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
		log.info("PaymentServiceImpl processPayment {}",request.getUserId());
		boolean flagIsValidOrder=orderFeignClient.validateOrder(request.getOrderId());
		
		if(!flagIsValidOrder)
			throw new ResourceNotFoundException("OrderId is invalid. Please enter a new valid order id");
		OrderResponseDTO order=orderFeignClient.getOrder(request.getOrderId());
		request.setUserId(order.getUserId());
		
		UserDto userDto = userIntegrationService.fetchUser(request); // will trigger circuitbreaker
		log.info("PaymentServiceImpl User API Success {}",userDto.getEmailAddress());

		Payment payment = new Payment();
		BeanUtils.copyProperties(request, payment);
		payment.setStatus("SUCCESS");
		payment.setAmount(order.getTotalPrice());
		paymentRepository.save(payment);
		return mapToDto(payment,userDto);
	}

	private PaymentResponseDTO mapToDto(Payment payment,UserDto userDto) {
		log.info("PaymentResponseDTO returning API Response");
		PaymentResponseDTO response = new PaymentResponseDTO();
		BeanUtils.copyProperties(payment, response);
		response.setPaymentId(payment.getId());
		response.setUserDto(userDto);
		return response;
	}
	
	
}
