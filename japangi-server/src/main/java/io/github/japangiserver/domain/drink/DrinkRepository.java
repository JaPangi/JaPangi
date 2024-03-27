package io.github.japangiserver.domain.drink;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findAllByIsDefault(Boolean isDefault);
}
