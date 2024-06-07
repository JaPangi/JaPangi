package io.github.japangiserver.domains.order.dto.response;

import lombok.Builder;

@Builder
public record DailyStatistics(
    String date,
    int totalPrice,
    int totalAmount
) {
}


