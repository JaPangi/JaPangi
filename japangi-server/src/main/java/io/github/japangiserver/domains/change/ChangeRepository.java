package io.github.japangiserver.domains.change;

import io.github.japangiserver.domains.money.MoneyEntity;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChangeRepository extends JpaRepository<ChangeEntity, Long> {

    @Query("select c from ChangeEntity c "
        + "where c.vendingMachineEntity.vendingMachineId =:vendingMachineId "
        + "order by c.moneyEntity.moneyId desc ")
    List<ChangeEntity> findAllByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);

    Optional<ChangeEntity> findByVendingMachineEntityAndMoneyEntity(
        VendingMachineEntity vendingMachineEntity, MoneyEntity moneyEntity);
}
