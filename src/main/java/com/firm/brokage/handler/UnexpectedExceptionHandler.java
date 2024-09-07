package com.firm.brokage.handler;

import com.firm.brokage.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UnexpectedExceptionHandler {

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		log.error("[UNEXPECTED-RUNTIME-EXCEPTION]", e);
		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.message("Unexpected internal error occurred")
				.build();
		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorApiResponse);
	}
}
