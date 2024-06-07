package io.github.japangiserver.domains.drink.serviceimpl;

import io.github.japangiserver.domains.drink.DrinkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrinkUpdater {
    private final DrinkReader drinkReader;
    public void modifyDrink(String drinkName, int drinkPrice, Long drinkId){
        DrinkEntity drinkEntity = drinkReader.getDrink(drinkId);
        drinkEntity.updateDrink(drinkName,drinkPrice);
    }
}
