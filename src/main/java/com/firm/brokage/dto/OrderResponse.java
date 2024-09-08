package com.firm.brokage.dto;

import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderSide;
import com.firm.brokage.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {

	private Long id;
	private Long customerId;
	private String assetName;
	private OrderSide orderSide;
	private Integer size;
	private Integer price;
	private OrderStatus status;
	private LocalDateTime createDate;

	public static OrderResponse fromOrder(Order order) {
		return OrderResponse.builder()
				.id(order.getId())
				.customerId(order.getCustomerId())
				.assetName(order.getAssetName())
				.orderSide(order.getOrderSide())
				.size(order.getSize())
				.price(order.getPrice())
				.status(order.getStatus())
				.createDate(new Timestamp(order.getCreateDate()).toLocalDateTime())
				.build();
	}
}
