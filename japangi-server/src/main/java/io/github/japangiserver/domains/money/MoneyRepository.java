package io.github.japangiserver.domains.money;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<MoneyEntity, Long> {
    Optional<MoneyEntity> findByValue(int value);
}
