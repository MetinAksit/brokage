package com.firm.brokage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firm.brokage.annotation.ValidOrderSide;
import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderSide;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

	@NotNull(message = "customer id must be supplied")
	private Long customer;

	@NotBlank(message = "asset name must be supplied")
	private String asset;

	@NotBlank(message = "order side must be supplied")
	@ValidOrderSide
	private String side;

	@NotNull(message = "order size must be supplied")
	@Min(value = 1, message = "order size must be positive")
	private Integer size;

	@NotNull(message = "order price must be supplied")
	@Min(value = 1, message = "order price must be positive")
	private Integer price;

	public static Order toOrder(OrderRequest request) {
		return Order.builder()
				.customerId(request.getCustomer())
				.assetName(request.getAsset())
				.orderSide(OrderSide.fromValue(request.getSide()))
				.size(request.getSize())
				.price(request.getPrice())
				.build();
	}
}
