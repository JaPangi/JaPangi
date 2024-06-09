package io.github.japangiserver.domains.drink.persistence.repository;

import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<DrinkEntity,Long> {
}
