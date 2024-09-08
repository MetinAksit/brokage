package com.firm.brokage.controller;

import com.firm.brokage.dto.AssetResponse;
import com.firm.brokage.entity.Asset;
import com.firm.brokage.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/asset")
public class AssetController {

	private final AssetService assetService;

	@GetMapping
	public ResponseEntity<List<AssetResponse>> listAssets(@RequestParam Long customer) {
		List<Asset> assets = assetService.listAssets(customer);

		return ResponseEntity.ok(
				assets.stream()
						.map(AssetResponse::fromAsset)
						.toList()
		);
	}
}
