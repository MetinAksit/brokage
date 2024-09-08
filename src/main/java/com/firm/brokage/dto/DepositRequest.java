package com.firm.brokage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firm.brokage.annotation.ValidEnum;
import com.firm.brokage.enums.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositRequest {

	@NotNull(message = "customer id must be supplied")
	private Long customer;

	@NotBlank(message = "currency must be supplied")
	@ValidEnum(enumClass = Currency.class, message = "currency must be supplied correctly")
	private String currency;

	@NotNull(message = "amount must be supplied")
	@Min(value = 1, message = "amount must be positive")
	private Integer amount;
}
