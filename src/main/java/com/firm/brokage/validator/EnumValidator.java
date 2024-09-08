package com.firm.brokage.validator;

import com.firm.brokage.annotation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

	private Enum<?>[] enumValues;

	@Override
	public void initialize(ValidEnum constraintAnnotation) {
		this.enumValues = constraintAnnotation.enumClass().getEnumConstants();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || Arrays.stream(enumValues)
				.anyMatch(element -> element.name().equalsIgnoreCase(value));
	}
}

