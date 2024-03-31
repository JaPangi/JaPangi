package io.github.japangiserver.domain.drink;

import io.github.japangiserver.domain.stock.Stock;
import lombok.Builder;

@Builder
public record DrinkStatus(
    long drinkId,
    String drinkName,
    int drinkPrice,
    String drinkImageUrl,
    int stock
) {

    public static DrinkStatus from(Stock stock) {
        return DrinkStatus.builder()
            .drinkId(stock.getDrink().getDrinkId())
            .drinkName(stock.getDrink().getDrinkName())
            .drinkPrice(stock.getDrink().getDrinkPrice())
            .drinkImageUrl(stock.getDrink().getDrinkImgUrl())
            .stock(stock.getAmount())
            .build();
    }
}
