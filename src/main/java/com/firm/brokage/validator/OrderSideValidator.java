package com.firm.brokage.validator;

import com.firm.brokage.annotation.ValidOrderSide;
import com.firm.brokage.enums.OrderSide;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSideValidator implements ConstraintValidator<ValidOrderSide, String> {

	@Override
	public void initialize(ValidOrderSide constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		return OrderSide.isValid(value);
	}
}
