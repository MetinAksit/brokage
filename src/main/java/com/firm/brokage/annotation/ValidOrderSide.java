package com.firm.brokage.annotation;

import com.firm.brokage.validator.OrderSideValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderSideValidator.class)
public @interface ValidOrderSide {

	String message() default "order side must be supplied correctly";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
