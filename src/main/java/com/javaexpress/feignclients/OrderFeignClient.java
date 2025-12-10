package com.javaexpress.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.OrderResponseDTO;


@FeignClient(name="order-service", path = "/api/orders")
@LoadBalancerClient
public interface OrderFeignClient {

	@GetMapping("/validate/{orderId}")
	public boolean validateOrder(@PathVariable Long orderId);
	
	@GetMapping("/{orderId}")
	public OrderResponseDTO getOrder(@PathVariable Long orderId);
}
