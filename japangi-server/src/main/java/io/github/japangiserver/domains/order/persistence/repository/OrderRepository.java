package io.github.japangiserver.domains.order.persistence.repository;

import io.github.japangiserver.domains.order.persistence.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o "
        + "where o.vendingMachineEntity.vendingMachineId = :vendingMachineId ")
    List<OrderEntity> findAllByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);

    @Query("select o "
        + "from OrderEntity o "
        + "where o.vendingMachineEntity.vendingMachineId = :vendingMachineId and "
        + "o.drinkEntity.drinkId = :drinkId")
    List<OrderEntity> findAllByVendingMachineAndDrink(
        @Param("vendingMachineId") Long vendingMachineId,
        @Param("drinkId") Long drinkId
    );
}

