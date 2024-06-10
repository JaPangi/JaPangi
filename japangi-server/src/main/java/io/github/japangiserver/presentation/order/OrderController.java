package io.github.japangiserver.presentation.order;

import io.github.japangiserver.domains.order.service.OrderService;
import io.github.japangiserver.presentation.order.dto.request.OrderRequest;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestBody;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

/** NOTE
 * 주문 기능 controller
 */
@SocketController
@RequestMapping(key = "ORDER")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /** NOTE
     * 자판기에서 주문하는 API
     * @param request 주문할 음료 Id(PK), 자판기 Id(PK), 클라이언트가 투입한 금액
     */
    @RequestMapping(key = "GET")
    public SocketResponse orderDrink(
        @RequestBody OrderRequest request
    ) {
        return SocketResponse.success(
            orderService.orderDrink(request.toOrderTarget(),request.toMoneyAmounts())
        );
    }
}