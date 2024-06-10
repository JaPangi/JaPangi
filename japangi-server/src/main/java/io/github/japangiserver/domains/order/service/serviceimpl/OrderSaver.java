package io.github.japangiserver.domains.order.service.serviceimpl;

import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import io.github.japangiserver.domains.drink.persistence.repository.DrinkRepository;
import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.order.persistence.entity.OrderEntity;
import io.github.japangiserver.domains.order.persistence.repository.OrderRepository;
import io.github.japangiserver.domains.vendingmachine.VendingMachine;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.repository.VendingMachineRepository;
import io.github.japangiserver.domains.vendingmachine.service.serviceimpl.VendingMachineReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * order 저장 implement Layer
 */
@Component
@RequiredArgsConstructor
public class OrderSaver{
    private final OrderRepository orderRepository;
    private final DrinkReader drinkReader;
    private final VendingMachineReader vendingMachineReader;
    private final DrinkRepository drinkRepository;
    private final VendingMachineRepository vendingMachineRepository;

    /** NOTE
     * 주문 저장 구현체
     * @param drinkId 주문한 음료 Id(PK)
     * @param vendingMachineId 주문한 자판기 Id(PK)
     */
    @Transactional
    public void saveOrder(Long drinkId, Long vendingMachineId){
        VendingMachine vendingMachine = vendingMachineReader.getVendingMachine(vendingMachineId);
        Drink drink = drinkReader.getDrink(drinkId);
        OrderEntity orderEntity = createOrder(drink, vendingMachine);
        orderRepository.save(orderEntity);
    }

    /** NOTE
     * 주문 entity 생성 구현체
     * @param drink drink domain
     * @param vendingMachine vendingMachine domain
     */
    public OrderEntity createOrder(Drink drink, VendingMachine vendingMachine){
        DrinkEntity drinkEntity = drinkRepository.findById(drink.drinkInfo().drinkId())
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));

        VendingMachineEntity vendingMachineEntity = vendingMachineRepository.findById(
                vendingMachine.vendingMachineId())
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다!"));

        return OrderEntity.builder()
            .drinkEntity(drinkEntity)
            .vendingMachineEntity(vendingMachineEntity)
            .build();
    }
}
