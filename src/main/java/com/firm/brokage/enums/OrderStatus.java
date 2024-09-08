package com.firm.brokage.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

	PENDING("PENDING"),
	MATCHED("MATCHED"),
	CANCELLED("CANCELLED");

	private final String value;

	public static OrderStatus fromValue(String value) {
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (orderStatus.getValue().equalsIgnoreCase(value)) {
				return orderStatus;
			}
		}
		throw new IllegalArgumentException("No matching OrderStatus for value: " + value);
	}

	public static boolean isValid(String value) {
		return Arrays.stream(OrderStatus.values())
				.anyMatch(orderStatus -> orderStatus.getValue().equalsIgnoreCase(value));
	}
}
