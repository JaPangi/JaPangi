package io.github.japangiserver.domains.order;

import io.github.japangiserver.domains.order.dto.request.OrderRequest;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestBody;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

@SocketController
@RequestMapping(key = "ORDER")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /** NOTE
     * 자판기에서 주문하는 API
     */
    @RequestMapping(key = "GET")
    public SocketResponse orderDrink(
        @RequestBody OrderRequest request
    ) {
        return SocketResponse.success(orderService.orderDrink(request));
    }
}