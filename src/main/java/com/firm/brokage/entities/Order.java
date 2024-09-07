package com.firm.brokage.entities;

import com.firm.brokage.enums.OrderSide;
import com.firm.brokage.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
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
	private OrderSide orderSide;

	@Column(name = "SIZE", nullable = false)
	private Integer size;

	@Column(name = "PRICE", nullable = false)
	private Integer price;

	@Column(name = "STATUS", nullable = false)
	private OrderStatus status;

	@Column(name = "CREATE_DATE", nullable = false, updatable = false)
	private Long createDate;

	@PrePersist
	void prePersist() {
		setCreateDate(Instant.now().toEpochMilli());
	}
}
