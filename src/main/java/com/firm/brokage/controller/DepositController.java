package com.firm.brokage.controller;

import com.firm.brokage.dto.DepositRequest;
import com.firm.brokage.dto.DepositResponse;
import com.firm.brokage.dto.WithdrawRequest;
import com.firm.brokage.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deposit")
public class DepositController {

	private final AssetService assetService;

	@PostMapping
	public ResponseEntity<DepositResponse> deposit(@Validated @RequestBody DepositRequest request) {
		var depositAsset = assetService.deposit(request.getCustomer(), request.getCurrency(), request.getAmount());

		return ResponseEntity.ok(DepositResponse.fromAsset(depositAsset));
	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> withdraw(@Validated @RequestBody WithdrawRequest request) {

		return null; // todo
	}
}
