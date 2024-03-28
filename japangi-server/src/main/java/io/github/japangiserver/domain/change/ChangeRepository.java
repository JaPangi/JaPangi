package io.github.japangiserver.domain.change;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChangeRepository extends JpaRepository<Change, Long> {
    List<Change> findAllByVendingMachineVendingMachineId(Long vendingMachineId);
}
