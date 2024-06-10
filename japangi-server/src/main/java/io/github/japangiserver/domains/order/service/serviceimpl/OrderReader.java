package io.github.japangiserver.domains.order.service.serviceimpl;

import io.github.japangiserver.domains.order.persistence.repository.OrderRepository;
import io.github.japangiserver.presentation.order.dto.response.OrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * order 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class OrderReader {
    private final OrderRepository orderRepository;

    /** NOTE
     * 자판기 매출 반환 구현체
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderListToVendingMachine(Long vendingMachineId){
        return orderRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(OrderResponse::fromEntity)
            .toList();
    }

    /** NOTE
     * 음료 매출 반환 구현체
     * @param vendingMachineId 자판기 Id(PK)
     * @param drinkId 음료 Id(PK)
     */
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderListToDrink(Long vendingMachineId, Long drinkId){
        return orderRepository.findAllByVendingMachineAndDrink(vendingMachineId, drinkId)
            .stream()
            .map(OrderResponse::fromEntity)
            .toList();
    }
}
