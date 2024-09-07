package com.firm.brokage.repository;

import com.firm.brokage.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByCustomerIdAndCreateDateBetween(Long customerId, Long from, Long to);
}
