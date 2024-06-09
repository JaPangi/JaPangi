package io.github.japangiserver.presentation.drink.dto.response;

import lombok.Builder;

@Builder
public record DrinkInfoResponse(String drinkName, int drinkPrice, String imageUrl) {

}
