package io.github.japangiserver.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByDrinkDrinkIdAndVendingMachineVendingMachineId(Long drink_drinkId, Long vendingMachine_vendingMachineId);
}
