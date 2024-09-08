package com.firm.brokage.dto;

import com.firm.brokage.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {

	private Long id;
	private Long customerId;
	private String assetName;
	private Integer size;
	private Integer usableSize;

	public static AssetResponse fromAsset(Asset asset) {
		return AssetResponse.builder()
				.id(asset.getId())
				.customerId(asset.getCustomerId())
				.assetName(asset.getAssetName())
				.size(asset.getSize())
				.usableSize(asset.getUsableSize())
				.build();
	}
}
