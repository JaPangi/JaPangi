package io.github.japangiserver.domains.drink.dto.response;

import io.github.japangiserver.domains.stock.adapter.persistence.entity.StockEntity;
import lombok.Builder;

@Builder
public record DrinkStatus(
    long drinkId,
    String drinkName,
    int drinkPrice,
    String drinkImageUrl,
    int stock
) {

    public static DrinkStatus from(StockEntity stockEntity) {
        return DrinkStatus.builder()
            .drinkId(stockEntity.getDrinkEntity().getDrinkId())
            .drinkName(stockEntity.getDrinkEntity().getDrinkName())
            .drinkPrice(stockEntity.getDrinkEntity().getDrinkPrice())
            .drinkImageUrl(stockEntity.getDrinkEntity().getDrinkImageUrl())
            .stock(stockEntity.getAmount())
            .build();
    }
}
