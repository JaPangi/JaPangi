package io.github.japangiserver.presentation.drink.dto.response;

import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import lombok.Builder;

@Builder
public record DrinkResponse(int drinkPrice, long drinkId) {
    public static DrinkResponse fromEntity(DrinkEntity drinkEntity){
        return DrinkResponse.builder()
            .drinkId(drinkEntity.getDrinkId())
            .drinkPrice(drinkEntity.getDrinkPrice())
            .build();
    }
}
