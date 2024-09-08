package com.firm.brokage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firm.brokage.annotation.ValidEnum;
import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderSide;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@Pattern(regexp = "(?i)^(?!TRY$).*", message = "please use deposit/withdrawal api for currency transactions")
	private String asset;

	@NotBlank(message = "order side must be supplied")
	@ValidEnum(enumClass = OrderSide.class, message = "order side must be supplied correctly")
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
				.orderSide(OrderSide.valueOf(request.getSide()))
				.size(request.getSize())
				.price(request.getPrice())
				.build();
	}
}
