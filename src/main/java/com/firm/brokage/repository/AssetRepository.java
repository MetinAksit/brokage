package com.firm.brokage.repository;

import com.firm.brokage.entity.Asset;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)	// enough for demo, use distributed locking in microservice env
	Asset findByCustomerIdAndAssetName(Long customerId, String assetName);

	List<Asset> findByCustomerId(Long customerId);
}
