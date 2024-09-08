package com.firm.brokage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawRequest extends DepositRequest {

	@NotBlank(message = "iban must be supplied")
	@Pattern(regexp = "^TR\\d{2}[A-Z0-9]{5}[A-Z0-9]\\d{16}$", message = "iban must be supplied correctly")
	private String iban;
}
