package io.github.japangiserver.domains.stock.application.dto.response;

import io.github.japangiserver.domains.stock.adapter.persistence.entity.StockEntity;
import lombok.Builder;

@Builder
public record StockStatus(String drinkName, int amount,String drinkImageUrl) {
    public static StockStatus fromEntity(StockEntity stockEntity){
        return StockStatus.builder()
            .drinkName(stockEntity.getDrinkEntity().getDrinkName())
            .amount(stockEntity.getAmount())
            .drinkImageUrl(stockEntity.getDrinkEntity().getDrinkImageUrl())
            .build();
    }
}
