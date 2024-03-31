package io.github.japangiserver.domain.order;

import java.time.LocalDateTime;

public record OrderSalesResponse(
    String dateTime,
    int totalPrice
) {

}
