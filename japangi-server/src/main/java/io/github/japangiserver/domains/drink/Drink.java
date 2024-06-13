package io.github.japangiserver.domains.drink;

import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import lombok.Builder;


/** NOTE
 * drink domain
 * @param drinkInfo 음료 Pk domain
 * @param drinkName 음료 이름
 * @param drinkPrice 음료 가격
 * @param drinkImageUrl 음료 이미지
 */
@Builder
public record Drink(
    DrinkInfo drinkInfo,
    String drinkName,
    String drinkImageUrl,
    int drinkPrice) {
    public static Drink toDrink(DrinkEntity drinkEntity){
        return Drink.builder()
            .drinkInfo(new DrinkInfo(drinkEntity.getDrinkId()))
            .drinkName(drinkEntity.getDrinkName())
            .drinkPrice(drinkEntity.getDrinkPrice())
            .build();
    }
}
