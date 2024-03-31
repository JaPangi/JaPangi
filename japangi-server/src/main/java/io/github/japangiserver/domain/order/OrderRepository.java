package io.github.japangiserver.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByVendingMachineVendingMachineId(Long vendingMachineId);
}
