package com.firm.brokage.dto;

import com.firm.brokage.entity.Asset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WithdrawResponse extends DepositResponse {

	private String iban;

	public static WithdrawResponse fromAsset(Asset asset) {
		var withdrawResponse = new WithdrawResponse();
		withdrawResponse.setId(asset.getId());
		withdrawResponse.setCustomerId(asset.getCustomerId());
		withdrawResponse.setAssetName(asset.getAssetName());
		withdrawResponse.setSize(asset.getSize());
		withdrawResponse.setUsableSize(asset.getUsableSize());
		return withdrawResponse;
	}
}
