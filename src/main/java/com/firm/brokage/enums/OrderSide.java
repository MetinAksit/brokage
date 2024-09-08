package com.firm.brokage.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderSide {

	BUY("BUY"),
	SELL("SELL");

	private final String value;

	public static OrderSide fromValue(String value) {
		for (OrderSide orderSide : OrderSide.values()) {
			if (orderSide.getValue().equalsIgnoreCase(value)) {
				return orderSide;
			}
		}
		throw new IllegalArgumentException("No matching OrderSide for value: " + value);
	}

	public static boolean isValid(String value) {
		return Arrays.stream(OrderSide.values())
				.anyMatch(orderSide -> orderSide.getValue().equalsIgnoreCase(value));
	}
}
