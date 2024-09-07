package com.firm.brokage.converter;

import com.firm.brokage.enums.OrderSide;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderSideConverter implements AttributeConverter<OrderSide, String> {

	@Override
	public String convertToDatabaseColumn(OrderSide orderSide) {
		return orderSide != null ? orderSide.getValue() : null;
	}

	@Override
	public OrderSide convertToEntityAttribute(String dbData) {
		return dbData != null ? OrderSide.fromValue(dbData) : null;
	}
}
