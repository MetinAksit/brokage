package com.firm.brokage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private Error error;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Error {

		private String code;
		private String message;
	}
}
