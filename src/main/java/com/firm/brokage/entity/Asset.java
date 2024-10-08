package com.firm.brokage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ASSET")
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private Long customerId;

	@Column(name = "ASSET_NAME", nullable = false)
	private String assetName;

	@Column(name = "SIZE", nullable = false)
	private Integer size;

	@Column(name = "USABLE_SIZE", nullable = false)
	private Integer usableSize;
}
