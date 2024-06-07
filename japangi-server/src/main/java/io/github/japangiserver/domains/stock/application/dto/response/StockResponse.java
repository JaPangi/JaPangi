package io.github.japangiserver.domains.stock.application.dto.response;

import lombok.Builder;

@Builder
public record StockResponse(int amount, long stockId) {
}
