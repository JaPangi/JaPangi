package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import io.github.japangiserver.domains.drink.persistence.repository.DrinkRepository;
import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import io.github.japangiserver.domains.stock.persistence.repository.StockRepository;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.repository.VendingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockCreator {
    public final static int DEFAULT_AMOUNT = 10;
    private final VendingMachineRepository  vendingMachineRepository;
    private final DrinkRepository drinkRepository;
    public StockEntity createStock(Long vendingMachineId, Drink drink){
        VendingMachineEntity vendingMachineEntity = vendingMachineRepository.findById(
                vendingMachineId
            )
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다!"));

        DrinkEntity drinkEntity = drinkRepository.findById(drink.drinkInfo().drinkId())
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));
        return StockEntity.builder()
            .amount(DEFAULT_AMOUNT)
            .vendingMachineEntity(vendingMachineEntity)
            .drinkEntity(drinkEntity)
            .build();
    }
}
