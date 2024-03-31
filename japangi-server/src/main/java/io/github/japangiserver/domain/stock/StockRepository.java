package io.github.japangiserver.domain.stock;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(
            "select s from Stock s " +
            "where s.drink.drinkId = :drinkId and s.vendingMachine.VendingMachineId = :vendingMachineId "
    )
    Optional<Stock> findByDrinkIdAndVendingMachineId(
            @Param("drinkId") Long drinkId,
            @Param("vendingMachineId") Long vendingMachineId
    );

    @Query("select s from Stock s where s.vendingMachine.VendingMachineId = :vendingMachineId ")
    List<Stock> findByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);
}
