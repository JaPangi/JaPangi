package io.github.japangiserver.presentation.stock.dto.response;

import lombok.Builder;

@Builder
public record StockResponse(int amount, long stockId) {
}
