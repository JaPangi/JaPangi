package io.github.japangiserver.domains.stock.persistence.repository;

import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    @Query(
        "select s from StockEntity s " +
            "where s.drinkEntity.drinkId = :drinkId and"
            + " s.vendingMachineEntity.vendingMachineId = :vendingMachineId "
    )
    Optional<StockEntity> findByDrinkIdAndVendingMachineId(
        @Param("drinkId") Long drinkId,
        @Param("vendingMachineId") Long vendingMachineId
    );

    @Query("select s "
        + "from StockEntity s"
        + " where s.vendingMachineEntity.vendingMachineId = :vendingMachineId ")
    List<StockEntity> findByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);
}
