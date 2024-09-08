package com.firm.brokage.service;

import com.firm.brokage.entity.Asset;
import com.firm.brokage.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssetService {

	private final AssetRepository assetRepository;

	public Asset deposit(Long customerId, String assetName, Integer amount) {
		// find customer's TRY asset
		var asset = assetRepository.findByCustomerIdAndAssetName(customerId, assetName);
		// if not exists, create
		if (asset == null) {
			asset = Asset.builder()
					.customerId(customerId)
					.assetName(assetName)
					.size(0)
					.usableSize(0)
					.build();
		}
		// add amount to size & usable size
		asset.setSize(asset.getSize() + amount);
		asset.setUsableSize(asset.getUsableSize() + amount);
		assetRepository.save(asset);
		return asset;
	}
}
