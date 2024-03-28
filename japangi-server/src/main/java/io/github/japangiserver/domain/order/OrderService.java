package io.github.japangiserver.domain.order;

import io.github.japangiserver.domain.change.Change;
import io.github.japangiserver.domain.change.ChangeProvider;
import io.github.japangiserver.domain.change.ChangeRepository;
import io.github.japangiserver.domain.drink.Drink;
import io.github.japangiserver.domain.drink.DrinkRepository;
import io.github.japangiserver.domain.stock.Stock;
import io.github.japangiserver.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final DrinkRepository drinkRepository;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final ChangeRepository changeRepository;
    private final ChangeProvider changeProvider;

    @Transactional
    public int orderDrink(OrderTarget orderTarget, List<MoneyAmount> moneyAmounts) {
        Drink drink = drinkRepository.findById(orderTarget.drinkId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않은 음료입니다"));

        List<Change> changeList = changeRepository.findAllByVendingMachineVendingMachineId(orderTarget.vendingMachineId());
        // [100 * 2 -> 200
        // 500 * 1] -> 500
        int totalInputPrice = moneyAmounts.stream()
                .mapToInt(moneyAmount -> moneyAmount.value() * moneyAmount.amount())
                .sum();

        if(drink.getDrinkPrice()>totalInputPrice) {
            throw new IllegalStateException("음료가격보다 낸 금액이 적습니다");
        }

        int changes = totalInputPrice - drink.getDrinkPrice();

        changeProvider.provide(changes,changeList); // 거스름돈 돌려주는애


        Stock stock = stockRepository.findByDrinkDrinkIdAndVendingMachineVendingMachineId(
                        orderTarget.drinkId(),
                        orderTarget.vendingMachineId()
                )
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));

        stock.removeAmount();
        return stock.getAmount();
    }
}
