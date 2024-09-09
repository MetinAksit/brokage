package com.firm.brokage.repository;

import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByCustomerIdAndCreateDateBetween(Long customerId, Long from, Long to);

	List<Order> findByCustomerIdAndCreateDateBetweenAndStatus(Long customerId, Long from, Long to, OrderStatus status);

	@Lock(LockModeType.PESSIMISTIC_WRITE)	// enough for demo, use distributed locking in microservice env
	Optional<Order> findById(Long id);
}
