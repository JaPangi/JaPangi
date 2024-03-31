package io.github.japangiserver.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.vendingMachine.VendingMachineId = :vendingMachineId ")
    List<Order> findAllByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);
}

