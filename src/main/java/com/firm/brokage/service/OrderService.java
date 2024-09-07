package com.firm.brokage.service;

import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderSide;
import com.firm.brokage.exception.BusinessException;
import com.firm.brokage.repository.AssetRepository;
import com.firm.brokage.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final AssetRepository assetRepository;

	public Order createOrder(Order order) {
		if (OrderSide.BUY.equals(order.getOrderSide())) {
			// check if price amount exists in customer TRY asset
			var tryAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), "TRY");
			if (tryAsset == null || tryAsset.getUsableSize() < order.getPrice()) {
				throw new BusinessException("Customer doesn't have enough deposit!");
			}

			// create order
			orderRepository.save(order);

			// subtract price from TRY usable size
			tryAsset.setUsableSize(tryAsset.getUsableSize() - order.getPrice());
			assetRepository.save(tryAsset);
		} else {
			// check if customer has enough of this asset
			var orderAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), order.getAssetName());
			if (orderAsset == null || orderAsset.getUsableSize() < order.getSize()) {
				throw new BusinessException("Customer doesn't have enough asset!");
			}

			// create order
			orderRepository.save(order);

			// subtract size from asset usable size
			orderAsset.setUsableSize(orderAsset.getUsableSize() - order.getSize());
			assetRepository.save(orderAsset);
		}

		return order;
	}
}
