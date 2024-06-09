package io.github.japangiserver.presentation.order.dto.response;

import lombok.Builder;

@Builder
public record DailyStatisticsResponse(
    String date,
    int totalPrice,
    int totalAmount
) {
}


