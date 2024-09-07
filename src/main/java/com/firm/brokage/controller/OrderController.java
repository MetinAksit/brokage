package com.firm.brokage.controller;

import com.firm.brokage.dto.OrderRequest;
import com.firm.brokage.dto.OrderResponse;
import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderSide;
import com.firm.brokage.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Validated @RequestBody OrderRequest request) {
		var order = Order.builder()
				.customerId(request.getCustomer())
				.assetName(request.getAsset())
				.orderSide(OrderSide.fromValue(request.getSide()))
				.size(request.getSize())
				.price(request.getPrice())
				.build();
		order = orderService.createOrder(order);

		var response = OrderResponse.builder()
				.customerId(order.getCustomerId())
				.assetName(order.getAssetName())
				.orderSide(order.getOrderSide())
				.size(order.getSize())
				.price(order.getPrice())
				.status(order.getStatus())
				.createDate(new Timestamp(order.getCreateDate()).toLocalDateTime())
				.build();

		return ResponseEntity.ok(response);
	}
}
