package io.github.japangiserver.domains.order;

import io.github.japangiserver.domains.change.serviceimpl.ChangeProvider;
import io.github.japangiserver.domains.change.serviceimpl.ChangeUpdater;
import io.github.japangiserver.domains.change.serviceimpl.ChangeValidator;
import io.github.japangiserver.domains.drink.dto.response.DrinkResponse;
import io.github.japangiserver.domains.drink.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.money.serviceimpl.MoneyValidator;
import io.github.japangiserver.domains.order.domain.MoneyAmounts;
import io.github.japangiserver.domains.order.domain.OrderTarget;
import io.github.japangiserver.domains.order.dto.request.OrderRequest;
import io.github.japangiserver.domains.order.dto.response.DailyStatistics;
import io.github.japangiserver.domains.order.dto.response.MonthlyStatistic;
import io.github.japangiserver.domains.order.dto.response.OrderResponse;
import io.github.japangiserver.domains.order.mapper.OrderTargetMapper;
import io.github.japangiserver.domains.order.serviceimpl.OrderCalculator;
import io.github.japangiserver.domains.order.serviceimpl.OrderReader;
import io.github.japangiserver.domains.order.serviceimpl.OrderSaver;
import io.github.japangiserver.domains.order.serviceimpl.OrderValidator;
import io.github.japangiserver.domains.order.serviceimpl.StatisticsCalculator;
import io.github.japangiserver.domains.stock.application.dto.response.StockResponse;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockUpdater;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockValidator;
import io.github.japangiserver.domains.vendingmachine.dto.response.VendingMachineResponse;
import io.github.japangiserver.domains.vendingmachine.serviceimpl.VendingMachineReader;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderSaver orderSaver;
    private final OrderReader orderReader;
    private final ChangeProvider changeProvider;
    private final MoneyValidator moneyValidator;
    private final ChangeValidator changeValidator;
    private final StockValidator stockValidator;
    private final ChangeUpdater changeUpdater;
    private final DrinkReader drinkReader;
    private final VendingMachineReader vendingMachineReader;
    private final StockReader stockReader;
    private final StatisticsCalculator statisticsCalculator;
    private final StockUpdater stockUpdater;
    private final OrderValidator orderValidator;
    private final OrderCalculator orderCalculator;
    private final OrderTargetMapper orderTargetMapper;

    @Transactional
    public MoneyAmounts orderDrink(OrderRequest orderRequest) {
        OrderTarget orderTarget = orderTargetMapper.toOrderTarget(
            orderRequest.drinkId(), orderRequest.vendingMachineId()
        );
        MoneyAmounts moneyAmounts = orderTargetMapper.toMoneyAmounts(orderRequest.moneyAmounts());

        moneyValidator.checkBillCount(moneyAmounts);

        DrinkResponse drink = drinkReader.getDrinkResponse(orderTarget.drinkId());

        VendingMachineResponse vendingMachine = vendingMachineReader.getVendingMachineResponse(
            orderTarget.vendingMachineId()
        );

        StockResponse stock = stockReader.getStockResponse(
            orderTarget.drinkId(), orderTarget.vendingMachineId()
        );

        stockValidator.validStock(stock.amount()); //재고가 남아있는지 확인
        //사용자가 넣은 잔돈 입금
        changeUpdater.insertChange(vendingMachine.vendingMachineId(), moneyAmounts);
        int totalPrice = moneyValidator.checkTotalCount(moneyAmounts); //3000

        orderValidator.checkInputMoney(drink.drinkPrice(),totalPrice);

        int changes = orderCalculator.calculateChanges(totalPrice, drink.drinkPrice());

        //거스름돈를 줄 수 있는지 검사
        changeValidator.validInputMoney(changes, orderTarget.vendingMachineId());
        orderSaver.saveOrder(drink.drinkId(), vendingMachine.vendingMachineId());

        stockUpdater.updateRemoveAmount(
            stockReader.getStockFromDrinkAndVendingMachine(orderTarget.drinkId(), orderTarget.vendingMachineId())
        );
        return changeProvider.provide(changes, orderTarget.vendingMachineId());
    }

    /**
     * NOTE
     * 각 자판기 각 음료별 일별/월별 매출
     */
    @Transactional(readOnly = true)
    public List<MonthlyStatistic> statisticsSales(Long vendingMachineId, Long drinkId) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        DateTimeFormatter monthFormatter = getMonthFormatter();

        List<OrderResponse> orderList = drinkId == 0L
            ? orderReader.getOrderListToVendingMachine(vendingMachineId)
            : orderReader.getOrderListToDrink(vendingMachineId, drinkId);

        List<DailyStatistics> dailyStatistics =
            statisticsCalculator.getDailyStatisticsList(orderList, dateTimeFormatter);

        return getStatistics(monthFormatter, dailyStatistics);
    }

    /**
     * NOTE
     * 월별 매출 구하는 로직
     */
    private List<MonthlyStatistic> getStatistics(
        DateTimeFormatter monthFormatter,
        List<DailyStatistics> dailyStatistics
    ) {
        return statisticsCalculator.getMonthlyStatisticList(monthFormatter, dailyStatistics);
    }

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static DateTimeFormatter getMonthFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM");
    }
}
