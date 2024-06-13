package io.github.japangiserver.presentation.stock.dto.response;

import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import lombok.Builder;

@Builder
public record StockInfoResponse(int amount) {
    public static StockInfoResponse fromEntity(StockEntity stockEntity){
        return StockInfoResponse.builder()
            .amount(stockEntity.getAmount())
            .build();
    }
}
