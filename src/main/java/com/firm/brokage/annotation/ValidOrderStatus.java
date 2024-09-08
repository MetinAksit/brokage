package com.firm.brokage.annotation;

import com.firm.brokage.validator.OrderStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderStatusValidator.class)
public @interface ValidOrderStatus {

	String message() default "order status must be supplied correctly";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
