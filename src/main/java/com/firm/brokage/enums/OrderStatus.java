package com.firm.brokage.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
}
