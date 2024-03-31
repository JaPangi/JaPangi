package io.github.japangiserver.domain.vendingmachine;

import io.github.japangiserver.domain.change.Change;
import io.github.japangiserver.domain.change.ChangeRepository;
import io.github.japangiserver.domain.drink.DrinkRepository;
import io.github.japangiserver.domain.money.MoneyRepository;
import io.github.japangiserver.domain.order.OrderTarget;
import io.github.japangiserver.domain.stock.Stock;
import io.github.japangiserver.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendingMachineService {
    private final static int DEFAULT_AMOUNT = 10;

    private final VendingMachineRepository vendingMachineRepository;
    private final DrinkRepository drinkRepository;
    private final MoneyRepository moneyRepository;
    private final StockRepository stockRepository;
    private final ChangeRepository changeRepository;

    @Transactional
    public long init() {
        VendingMachine vendingMachine = new VendingMachine();
        Long vendingMachineId = vendingMachineRepository.save(vendingMachine).getVendingMachineId();
        drinkRepository.findAll()
                .forEach(drink -> {
                    Stock stock = Stock.builder()
                            .amount(DEFAULT_AMOUNT)
                            .vendingMachine(vendingMachine)
                            .drink(drink)
                            .build();
                    stockRepository.save(stock);
                });

        moneyRepository.findAll()
                .forEach(money -> {
                    Change change = Change.builder()
                            .vendingMachine(vendingMachine)
                            .amount(DEFAULT_AMOUNT)
                            .money(money)
                            .build();
                    changeRepository.save(change);
                });
        return vendingMachineId;
    }

    public void checkStock(OrderTarget orderTarget){
        Stock stock = stockRepository.findByDrinkDrinkIdAndVendingMachineVendingMachineId(
                        orderTarget.drinkId(),
                        orderTarget.vendingMachineId()
                )
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다"));

        if(stock.getAmount()==0)
            throw new IllegalStateException("재고가 부족합니다.");
    }
}
