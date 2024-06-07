package io.github.japangiserver.domains.order.serviceimpl;

import io.github.japangiserver.domains.order.OrderRepository;
import io.github.japangiserver.domains.order.dto.response.OrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReader {
    private final OrderRepository orderRepository;

    public List<OrderResponse> getOrderListToVendingMachine(Long vendingMachineId){
        return orderRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(OrderResponse::fromEntity)
            .toList();
    }

    public List<OrderResponse> getOrderListToDrink(Long vendingMachineId, Long drinkId){
        return orderRepository.findAllByVendingMachineAndDrink(vendingMachineId, drinkId)
            .stream()
            .map(OrderResponse::fromEntity)
            .toList();
    }
}
