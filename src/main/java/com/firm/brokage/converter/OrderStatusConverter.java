package com.firm.brokage.converter;

import com.firm.brokage.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

	@Override
	public String convertToDatabaseColumn(OrderStatus orderStatus) {
		return orderStatus != null ? orderStatus.getValue() : null;
	}

	@Override
	public OrderStatus convertToEntityAttribute(String dbData) {
		return dbData != null ? OrderStatus.fromValue(dbData) : null;
	}
}
