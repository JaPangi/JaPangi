package io.github.japangiserver.domains.stock.application.serviceimpl;

import io.github.japangiserver.domains.drink.DrinkEntity;
import io.github.japangiserver.domains.stock.adapter.persistence.entity.StockEntity;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import org.springframework.stereotype.Component;

@Component
public class StockCreator {
    public final static int DEFAULT_AMOUNT = 10;

    public StockEntity createStock(
        VendingMachineEntity vendingMachine,
        DrinkEntity drink
        ){
        return StockEntity.builder()
            .amount(DEFAULT_AMOUNT)
            .vendingMachineEntity(vendingMachine)
            .drinkEntity(drink)
            .build();
    }
}
