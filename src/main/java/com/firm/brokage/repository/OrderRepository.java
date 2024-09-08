package com.firm.brokage.repository;

import com.firm.brokage.entity.Order;
import com.firm.brokage.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByCustomerIdAndCreateDateBetween(Long customerId, Long from, Long to);

	List<Order> findByCustomerIdAndCreateDateBetweenAndStatus(Long customerId, Long from, Long to, OrderStatus status);
}
