package com.firm.brokage.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
}
