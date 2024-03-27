package io.github.japangiserver.domain.vendingmachine;

import io.github.japangiserver.domain.change.Change;
import io.github.japangiserver.domain.change.ChangeRepository;
import io.github.japangiserver.domain.drink.DrinkRepository;
import io.github.japangiserver.domain.money.MoneyRepository;
import io.github.japangiserver.domain.stock.Stock;
import io.github.japangiserver.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendingMachineService {

    private static final int DEFAULT_AMOUNT = 10;

    private final VendingMachineRepository vendingMachineRepository;
    private final MoneyRepository moneyRepository;
    private final ChangeRepository changeRepository;
    private final DrinkRepository drinkRepository;
    private final StockRepository stockRepository;

    @Transactional
    public Long init() {
        VendingMachine vendingMachine = new VendingMachine();
        long vendingMachineId = vendingMachineRepository.save(vendingMachine).getVendingMachineId();

        drinkRepository.findAllByIsDefault(true).forEach(drink -> {
            Stock stock = Stock
                    .builder()
                    .drink(drink)
                    .amount(DEFAULT_AMOUNT)
                    .vendingMachine(vendingMachine)
                    .build();
            stockRepository.save(stock);
        });

        moneyRepository.findAll().forEach(money -> {
            Change change = Change.builder()
                    .money(money)
                    .amount(DEFAULT_AMOUNT)
                    .vendingMachine(vendingMachine)
                    .build();
            changeRepository.save(change);
        });

        return vendingMachineId;
    }
}
