package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import io.github.japangiserver.domains.stock.persistence.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StockSaver {

    private final StockRepository stockRepository;
    private final DrinkReader drinkReader;
    private final StockCreator stockCreator;

    @Transactional
    public void initStock(Long vendingMachineId) {
        drinkReader.getDrinkList()
            .forEach(stock -> {
                StockEntity stockEntity = stockCreator.createStock(vendingMachineId , stock);
                stockRepository.save(stockEntity);
            });
    }
}
