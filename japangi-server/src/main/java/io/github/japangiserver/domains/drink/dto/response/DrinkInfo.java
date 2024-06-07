package io.github.japangiserver.domains.drink.dto.response;

import lombok.Builder;

@Builder
public record DrinkInfo(String drinkName, int drinkPrice,String imageUrl) {

}
