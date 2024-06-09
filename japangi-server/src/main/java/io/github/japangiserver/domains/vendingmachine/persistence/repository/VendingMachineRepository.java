package io.github.japangiserver.domains.vendingmachine.persistence.repository;

import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingMachineRepository extends JpaRepository<VendingMachineEntity, Long> {
}
