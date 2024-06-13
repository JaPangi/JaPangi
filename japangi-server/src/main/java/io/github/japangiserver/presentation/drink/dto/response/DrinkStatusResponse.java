package io.github.japangiserver.presentation.drink.dto.response;

import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import lombok.Builder;

@Builder
public record DrinkStatusResponse(
    long drinkId,
    String drinkName,
    int drinkPrice,
    String drinkImageUrl,
    int stock
) {

    public static DrinkStatusResponse fromStockEntity(StockEntity stockEntity) {
        return DrinkStatusResponse.builder()
            .drinkId(stockEntity.getDrinkEntity().getDrinkId())
            .drinkName(stockEntity.getDrinkEntity().getDrinkName())
            .drinkPrice(stockEntity.getDrinkEntity().getDrinkPrice())
            .drinkImageUrl(stockEntity.getDrinkEntity().getDrinkImageUrl())
            .stock(stockEntity.getAmount())
            .build();
    }
}
