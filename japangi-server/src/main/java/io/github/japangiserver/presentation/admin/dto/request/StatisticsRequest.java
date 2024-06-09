package io.github.japangiserver.presentation.admin.dto.request;

import io.github.japangiserver.domains.drink.DrinkInfo;

public record StatisticsRequest(long drinkId) {
    public DrinkInfo toDrinkInfo(){
        return new DrinkInfo(drinkId);
    }
}
