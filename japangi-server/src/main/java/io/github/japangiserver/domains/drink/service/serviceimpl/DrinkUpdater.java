package io.github.japangiserver.domains.drink.service.serviceimpl;

import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import io.github.japangiserver.domains.drink.persistence.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DrinkUpdater {
    private final DrinkRepository drinkRepository;
    @Transactional
    public void modifyDrink(Drink drink){
        DrinkEntity drinkEntity = drinkRepository.findById(drink.drinkInfo().drinkId())
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));
        drinkEntity.updateDrink(drink.drinkName(),drink.drinkPrice());
    }
}
