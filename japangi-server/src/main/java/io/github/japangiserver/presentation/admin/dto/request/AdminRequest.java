package io.github.japangiserver.presentation.admin.dto.request;

import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.DrinkInfo;

public record AdminRequest(
    int drinkPrice,
    String drinkName,
    String drinkImageUrl
) {
    public Drink toDrink(long drinkId){
        return new Drink(
            new DrinkInfo(drinkId),
            drinkName,
            drinkImageUrl,
            drinkPrice
        );
    }
}
