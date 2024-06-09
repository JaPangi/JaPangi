package io.github.japangiserver.presentation.order.dto.response;

import io.github.japangiserver.domains.order.persistence.entity.OrderEntity;
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
