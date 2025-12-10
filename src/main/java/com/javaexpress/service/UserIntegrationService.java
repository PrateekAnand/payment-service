package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.PaymentRequestDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.feignclients.UserFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserIntegrationService {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@CircuitBreaker(name="userServiceCB",fallbackMethod = "userFallBack")
	//@Retry
	public UserDto fetchUser(PaymentRequestDTO request) {
		log.info("UserIntegrationService fetchUser {}",request.getUserId());
		UserDto userDto = userFeignClient.fetchUser(request.getUserId().intValue());
		if(userDto == null) {
			throw new RuntimeException("User Not Found in Db");
		}
		return userDto;
	}
	
	public UserDto userFallBack(PaymentRequestDTO request,Throwable t) {
		log.error("User Service FallBack {}",t.getMessage());
		throw new RuntimeException("User service is unavailable. Please try again later.");
	}
	
}
