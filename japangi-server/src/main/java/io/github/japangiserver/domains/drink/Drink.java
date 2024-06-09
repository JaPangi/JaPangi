package io.github.japangiserver.domains.drink;

import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import lombok.Builder;

@Builder
public record Drink(
    DrinkInfo drinkInfo,
    String drinkName,
    int drinkPrice) {
    public static Drink toDrink(DrinkEntity drinkEntity){
        return Drink.builder()
            .drinkInfo(new DrinkInfo(drinkEntity.getDrinkId()))
            .drinkName(drinkEntity.getDrinkName())
            .drinkPrice(drinkEntity.getDrinkPrice())
            .build();
    }
}
