package com.firm.brokage.annotation;

import com.firm.brokage.validator.CurrencyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrencyValidator.class)
public @interface ValidCurrency {

	String message() default "currency must be supplied correctly";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
