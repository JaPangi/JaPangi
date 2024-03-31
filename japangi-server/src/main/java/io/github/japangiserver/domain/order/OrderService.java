package io.github.japangiserver.domain.order;

import io.github.japangiserver.domain.change.Change;
import io.github.japangiserver.domain.change.ChangeProvider;
import io.github.japangiserver.domain.change.ChangeRepository;
import io.github.japangiserver.domain.drink.Drink;
import io.github.japangiserver.domain.drink.DrinkRepository;
import io.github.japangiserver.domain.stock.Stock;
import io.github.japangiserver.domain.stock.StockRepository;
import io.github.japangiserver.domain.vendingmachine.VendingMachineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final DrinkRepository drinkRepository;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final ChangeRepository changeRepository;
    private final ChangeProvider changeProvider;

    @Transactional
    public MoneyAmounts orderDrink(OrderTarget orderTarget, MoneyAmounts moneyAmounts) {
        Drink drink = drinkRepository.findById(orderTarget.drinkId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않은 음료입니다"));

        Stock stock = stockRepository.findByDrinkIdAndVendingMachineId(
                        orderTarget.drinkId(),
                        orderTarget.vendingMachineId()
                )
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));

        List<Change> changeList = changeRepository.findAllByVendingMachineId(
                orderTarget.vendingMachineId()
        );

        int totalPrice = moneyAmounts.calculateTotalPrice();

        if (drink.getDrinkPrice() > totalPrice) {
            throw new IllegalStateException("음료가격보다 낸 금액이 적습니다");
        }

        int changes = totalPrice - drink.getDrinkPrice();

        stock.removeAmount();
        return changeProvider.provide(changes, changeList);
    }

    /**
     * NOTE
     * 전체 자판기 일별 매출
     */
    @Transactional(readOnly = true)
    public List<OrderSalesResponse> dailyVendingMachineSales(
            VendingMachineRequest vendingMachineRequest) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Map<String, Integer> collect = orderRepository.findAllByVendingMachineId(
                        vendingMachineRequest.vendingMachineId()
                )
                .stream()
                .collect(Collectors.groupingBy(order -> order.getOrderedAt().format(dateTimeFormatter),
                        Collectors.summingInt(orders -> orders.getDrink().getDrinkPrice())));

        return collect.entrySet().stream()
                .map(order -> new OrderSalesResponse(order.getKey(), order.getValue()))
                .sorted(Comparator.comparing(OrderSalesResponse::dateTime))
                .collect(Collectors.toList());
    }

    /**
     * NOTE
     * 전체 자판기 월별 매출
     */
    @Transactional(readOnly = true)
    public List<OrderSalesResponse> monthlyVendingMachineSales(
            VendingMachineRequest vendingMachineRequest) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

        Map<String, Integer> collect = orderRepository.findAllByVendingMachineId(
                        vendingMachineRequest.vendingMachineId()
                )
                .stream()
                .collect(Collectors.groupingBy(order -> order.getOrderedAt().format(dateTimeFormatter),
                        Collectors.summingInt(orders -> orders.getDrink().getDrinkPrice())));

        return collect.entrySet().stream()
                .map(order -> new OrderSalesResponse(order.getKey(), order.getValue()))
                .sorted(Comparator.comparing(OrderSalesResponse::dateTime))
                .collect(Collectors.toList());
    }
}
