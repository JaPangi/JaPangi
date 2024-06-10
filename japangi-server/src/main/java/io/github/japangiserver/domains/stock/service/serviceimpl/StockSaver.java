package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import io.github.japangiserver.domains.stock.persistence.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 재고 저장 implement Layer
 */
@Component
@RequiredArgsConstructor
public class StockSaver {

    private final StockRepository stockRepository;
    private final DrinkReader drinkReader;
    private final StockCreator stockCreator;

    /** NOTE
     * 재고 초기 셋팅 구현체
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional
    public void initStock(Long vendingMachineId) {
        drinkReader.getDrinkList()
            .forEach(stock -> {
                StockEntity stockEntity = stockCreator.createStock(vendingMachineId , stock);
                stockRepository.save(stockEntity);
            });
    }
}
