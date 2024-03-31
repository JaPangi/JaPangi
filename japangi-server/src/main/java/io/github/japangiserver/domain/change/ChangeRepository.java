package io.github.japangiserver.domain.change;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChangeRepository extends JpaRepository<Change, Long> {

    @Query("select c from Change c where c.vendingMachine.VendingMachineId = :vendingMachineId ")
    List<Change> findAllByVendingMachineId(@Param("vendingMachineId") Long vendingMachineId);
}
