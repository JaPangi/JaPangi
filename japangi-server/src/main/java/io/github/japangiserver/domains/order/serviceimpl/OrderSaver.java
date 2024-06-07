package io.github.japangiserver.domains.order.serviceimpl;

import io.github.japangiserver.domains.drink.DrinkEntity;
import io.github.japangiserver.domains.drink.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.order.OrderEntity;
import io.github.japangiserver.domains.order.OrderRepository;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.serviceimpl.VendingMachineReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSaver{
    private final OrderRepository orderRepository;
    private final DrinkReader drinkReader;
    private final VendingMachineReader vendingMachineReader;

    public void saveOrder(Long drinkId, Long vendingMachineId){
        VendingMachineEntity vendingMachineEntity = vendingMachineReader.getVendingMachine(vendingMachineId);
        DrinkEntity drinkEntity = drinkReader.getDrink(drinkId);
        OrderEntity orderEntity = createOrder(drinkEntity, vendingMachineEntity);
        orderRepository.save(orderEntity);
    }

    public OrderEntity createOrder(DrinkEntity drinkEntity, VendingMachineEntity vendingMachineEntity){
        return OrderEntity.builder()
            .drinkEntity(drinkEntity)
            .vendingMachineEntity(vendingMachineEntity)
            .build();
    }
}
