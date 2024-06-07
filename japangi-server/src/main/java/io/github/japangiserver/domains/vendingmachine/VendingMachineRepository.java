package io.github.japangiserver.domains.vendingmachine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingMachineRepository extends JpaRepository<VendingMachineEntity, Long> {
}
