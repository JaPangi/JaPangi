package io.github.japangiserver.presentation.stock.dto.response;

import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import lombok.Builder;

@Builder
public record StockStatusResponse(String drinkName, int amount, String drinkImageUrl) {
    public static StockStatusResponse fromEntity(StockEntity stockEntity){
        return StockStatusResponse.builder()
            .drinkName(stockEntity.getDrinkEntity().getDrinkName())
            .amount(stockEntity.getAmount())
            .drinkImageUrl(stockEntity.getDrinkEntity().getDrinkImageUrl())
            .build();
    }
}
