package com.firm.brokage.handler;

import com.firm.brokage.dto.ErrorResponse;
import com.firm.brokage.exception.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.build();

		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.badRequest().body(errorApiResponse);
	}

	@ExceptionHandler({MissingServletRequestParameterException.class})
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(e.getMessage())
				.build();

		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.badRequest().body(errorApiResponse);
	}

	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(e.getMessage())
				.build();

		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.badRequest().body(errorApiResponse);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Optional<String> message;
		try {
			message = e.getBindingResult().getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.filter(Objects::nonNull)
					.findFirst();
		} catch (Exception ex) {
			message = Optional.empty();
		}

		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(message.orElseGet(HttpStatus.BAD_REQUEST::getReasonPhrase))
				.build();

		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.badRequest().body(errorApiResponse);
	}

	@ExceptionHandler({HandlerMethodValidationException.class})
	public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
		Optional<String> message;
		try {
			message = Optional.of(Objects.requireNonNull(e.getAllValidationResults().get(0).getResolvableErrors().get(0).getDefaultMessage()));
		} catch (Exception ex) {
			message = Optional.empty();
		}

		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(message.orElseGet(HttpStatus.BAD_REQUEST::getReasonPhrase))
				.build();

		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.badRequest().body(errorApiResponse);
	}

	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
		ErrorResponse.Error error = ErrorResponse.Error.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(e.getMessage())
				.build();

		ErrorResponse errorApiResponse = ErrorResponse.builder()
				.error(error)
				.build();
		return ResponseEntity.badRequest().body(errorApiResponse);
	}
}
