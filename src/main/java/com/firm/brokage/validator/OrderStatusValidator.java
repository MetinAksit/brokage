package com.firm.brokage.validator;

import com.firm.brokage.annotation.ValidOrderStatus;
import com.firm.brokage.enums.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {

	@Override
	public void initialize(ValidOrderStatus constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		try {
			return value == null || OrderStatus.fromValue(value) != null;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
