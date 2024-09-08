package com.firm.brokage.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Currency {

	TRY("TRY");

	private final String value;

	public static Currency fromValue(String value) {
		for (Currency currency : Currency.values()) {
			if (currency.getValue().equalsIgnoreCase(value)) {
				return currency;
			}
		}
		throw new IllegalArgumentException("No matching Currency for value: " + value);
	}

	public static boolean isValid(String value) {
		return Arrays.stream(Currency.values())
				.anyMatch(currency -> currency.getValue().equalsIgnoreCase(value));
	}
}
