package io.github.japangiserver.domains.order.dto.response;

import io.github.japangiserver.domains.order.OrderEntity;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderResponse(
    LocalDateTime orderAt,
    int drinkPrice
    ) {

    public static OrderResponse fromEntity(OrderEntity orderEntity){
        return OrderResponse.builder()
            .orderAt(orderEntity.getOrderedAt())
            .drinkPrice(orderEntity.getDrinkEntity().getDrinkPrice())
            .build();
    }
}
