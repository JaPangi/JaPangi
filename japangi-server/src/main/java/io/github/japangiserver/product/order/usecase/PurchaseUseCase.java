package io.github.japangiserver.product.order.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.usecase.BaseUseCase;
import io.github.japangiserver.domain.order.OrderService;
import io.github.japangiserver.product.order.dto.request.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class PurchaseUseCase extends BaseUseCase<OrderRequest> {

    private final OrderService orderService;

    public PurchaseUseCase(ObjectMapper objectMapper, OrderService orderService) {
        super(objectMapper);
        this.orderService = orderService;
    }

    @Override
    public SocketResponse core(OrderRequest request) {
        orderService.orderDrink(request.toTarget(), request.toMoneyAmounts());
        return null;
    }

    @Override
    public Class<OrderRequest> support() {
        return OrderRequest.class;
    }
}
