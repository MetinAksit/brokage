package com.firm.brokage.service;

import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.Currency;
import com.firm.brokage.enums.OrderSide;
import com.firm.brokage.enums.OrderStatus;
import com.firm.brokage.exception.BusinessException;
import com.firm.brokage.repository.AssetRepository;
import com.firm.brokage.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final AssetRepository assetRepository;

	public Order createOrder(Order order) {
		if (OrderSide.BUY.equals(order.getOrderSide())) {
			// check if price amount exists in customer TRY asset
			var tryAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), Currency.TRY.name());
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

	public List<Order> listOrders(Long customer, Long from, Long to) {
		return orderRepository.findByCustomerIdAndCreateDateBetween(customer, from, to);
	}

	public List<Order> listOrders(Long customer, Long from, Long to, OrderStatus status) {
		return orderRepository.findByCustomerIdAndCreateDateBetweenAndStatus(customer, from, to, status);
	}

	public Order deleteOrder(Long id) {
		// check if there is such order
		var order = orderRepository.findById(id).orElseThrow(() -> new BusinessException("Invalid order id!"));

		// check if status is pending
		if (!OrderStatus.PENDING.equals(order.getStatus())) {
			throw new BusinessException("Invalid order status!");
		}

		if (OrderSide.BUY.equals(order.getOrderSide())) {
			// if buy, find customer try asset, add price to usable size
			var tryAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), Currency.TRY.name());
			tryAsset.setUsableSize(tryAsset.getUsableSize() + order.getPrice());
			assetRepository.save(tryAsset);
		} else if (OrderSide.SELL.equals(order.getOrderSide())) {
			// if sell, find customer asset, add size to usable size
			var orderAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), order.getAssetName());
			orderAsset.setUsableSize(orderAsset.getUsableSize() + order.getSize());
			assetRepository.save(orderAsset);
		}

		// make order status cancelled
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);

		return order;
	}
}
