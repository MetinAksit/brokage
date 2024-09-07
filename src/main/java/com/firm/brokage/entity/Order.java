package com.firm.brokage.entity;

import com.firm.brokage.converter.OrderSideConverter;
import com.firm.brokage.converter.OrderStatusConverter;
import com.firm.brokage.enums.OrderSide;
import com.firm.brokage.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"ORDER\"")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private Long customerId;

	@Column(name = "ASSET_NAME", nullable = false)
	private String assetName;

	@Column(name = "ORDER_SIDE", nullable = false)
	@Convert(converter = OrderSideConverter.class)
	private OrderSide orderSide;

	@Column(name = "SIZE", nullable = false)
	private Integer size;

	@Column(name = "PRICE", nullable = false)
	private Integer price;

	@Column(name = "STATUS", nullable = false)
	@Convert(converter = OrderStatusConverter.class)
	private OrderStatus status;

	@Column(name = "CREATE_DATE", nullable = false, updatable = false)
	private Long createDate;

	@PrePersist
	void prePersist() {
		setStatus(OrderStatus.PENDING);
		setCreateDate(Instant.now().toEpochMilli());
	}
}
