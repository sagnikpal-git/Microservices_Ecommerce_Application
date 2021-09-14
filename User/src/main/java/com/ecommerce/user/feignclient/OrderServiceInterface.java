package com.ecommerce.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.user.dto.OrderHistoryDTO;
import com.ecommerce.user.dto.OrderProductDTO;
import com.ecommerce.user.dto.SearchDTO;
import com.ecommerce.user.dto.SearchRequest;
import com.ecommerce.user.exception.InSufficientBalanceException;
import com.ecommerce.user.exception.ResourceNotFoundException;

@FeignClient(name="http://ORDER-SERVICE:9002/api/orderservice")
public interface OrderServiceInterface
{
	
	@PostMapping("/products/searchproducts")
	public ResponseEntity<List<SearchDTO>> getCategoryProductDetails(@RequestBody SearchRequest request);
	
	@PostMapping("/")
	public ResponseEntity<String> productOrder(@RequestBody OrderProductDTO orderProDto)  throws InSufficientBalanceException, ResourceNotFoundException;
	
	@GetMapping("/orderHistory/{userName}/{userId}")
	public ResponseEntity<List<OrderHistoryDTO>> orderHistory(@PathVariable("userName") String userName,@PathVariable("userId") String userId);
}	