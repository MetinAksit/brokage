package com.firm.brokage.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderSide {

	BUY("BUY"),
	SELL("SELL");

	private final String value;
}
