package io.github.japangiserver.domains.stock.application.serviceimpl;

import io.github.japangiserver.domains.drink.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.stock.adapter.persistence.entity.StockEntity;
import io.github.japangiserver.domains.stock.adapter.persistence.repository.StockRepository;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockSaver {

    private final StockRepository stockRepository;
    private final DrinkReader drinkReader;
    private final StockCreator stockCreator;

    public void initStock(VendingMachineEntity vendingMachine) {
        drinkReader.getDrinkList()
            .forEach(stock -> {
                StockEntity stockEntity = stockCreator.createStock(vendingMachine, stock);
                stockRepository.save(stockEntity);
            });
    }
}
