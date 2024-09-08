package com.firm.brokage.validator;

import com.firm.brokage.annotation.ValidCurrency;
import com.firm.brokage.enums.Currency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

	@Override
	public void initialize(ValidCurrency constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		return Currency.isValid(value);
	}
}
