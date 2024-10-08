package com.firm.brokage.controller;

import com.firm.brokage.annotation.ValidEnum;
import com.firm.brokage.dto.OrderRequest;
import com.firm.brokage.dto.OrderResponse;
import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderStatus;
import com.firm.brokage.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Validated @RequestBody OrderRequest request) {
		var order = orderService.createOrder(OrderRequest.toOrder(request));

		return ResponseEntity.ok(OrderResponse.fromOrder(order));
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> listOrders(
			@RequestParam Long customer,
			@RequestParam LocalDateTime from,
			@RequestParam LocalDateTime to,
			@RequestParam(required = false) @ValidEnum(enumClass = OrderStatus.class, message = "order status must be supplied correctly") String status
	) {
		var fromEpoch = from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		var toEpoch = to.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		List<Order> orders;
		if (StringUtils.hasText(status)) {
			orders = orderService.listOrders(customer, fromEpoch, toEpoch, OrderStatus.valueOf(status));
		} else {
			orders = orderService.listOrders(customer, fromEpoch, toEpoch);
		}

		return ResponseEntity.ok(
				orders.stream()
						.map(OrderResponse::fromOrder)
						.toList()
		);
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long orderId) {
		var order = orderService.deleteOrder(orderId);
		return ResponseEntity.ok(OrderResponse.fromOrder(order));
	}
}
