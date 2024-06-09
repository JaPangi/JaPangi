package io.github.japangiserver.domains.order.service.serviceimpl;

import io.github.japangiserver.domains.order.persistence.repository.OrderRepository;
import io.github.japangiserver.presentation.order.dto.response.OrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderReader {
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderListToVendingMachine(Long vendingMachineId){
        return orderRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(OrderResponse::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderListToDrink(Long vendingMachineId, Long drinkId){
        return orderRepository.findAllByVendingMachineAndDrink(vendingMachineId, drinkId)
            .stream()
            .map(OrderResponse::fromEntity)
            .toList();
    }
}
