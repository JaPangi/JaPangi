package io.github.japangiserver.domains.order.service;

import static io.github.japangiserver.util.datefomat.DateFormatter.getDateFormatter;
import static io.github.japangiserver.util.datefomat.DateFormatter.getMonthFormatter;

import io.github.japangiserver.domains.change.serviceimpl.ChangeProvider;
import io.github.japangiserver.domains.change.serviceimpl.ChangeUpdater;
import io.github.japangiserver.domains.change.serviceimpl.ChangeValidator;
import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.DrinkInfo;
import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.money.serviceimpl.MoneyValidator;
import io.github.japangiserver.domains.order.MoneyAmounts;
import io.github.japangiserver.domains.order.OrderTarget;
import io.github.japangiserver.domains.order.service.serviceimpl.OrderCalculator;
import io.github.japangiserver.domains.order.service.serviceimpl.OrderReader;
import io.github.japangiserver.domains.order.service.serviceimpl.OrderSaver;
import io.github.japangiserver.domains.order.service.serviceimpl.OrderValidator;
import io.github.japangiserver.domains.order.service.serviceimpl.StatisticsCalculator;
import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockUpdater;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockValidator;
import io.github.japangiserver.domains.vendingmachine.VendingMachine;
import io.github.japangiserver.domains.vendingmachine.service.serviceimpl.VendingMachineReader;
import io.github.japangiserver.presentation.order.dto.response.DailyStatisticsResponse;
import io.github.japangiserver.presentation.order.dto.response.MonthlyStatisticResponse;
import io.github.japangiserver.presentation.order.dto.response.OrderResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 주문 핵심 비즈니스 service Layer
 */
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

    /** NOTE
     * 클라이언트의 주문을 처리하는 구현체
     */
    @Transactional
    public MoneyAmounts orderDrink(OrderTarget orderTarget, MoneyAmounts moneyAmounts) {
        moneyValidator.checkBillCount(moneyAmounts);

        Drink drink = drinkReader.getDrink(orderTarget.drinkId());

        VendingMachine vendingMachine = vendingMachineReader.getVendingMachine(
            orderTarget.vendingMachineId()
        );

        AddStock stock = stockReader.getStock(
            orderTarget.drinkId(), orderTarget.vendingMachineId()
        );

        stockValidator.validStock(stock.amount()); //재고가 남아있는지 확인

        changeUpdater.insertChange(vendingMachine.vendingMachineId(), moneyAmounts); //사용자가 넣은 잔돈 입금
        int totalPrice = moneyValidator.checkTotalCount(moneyAmounts);

        orderValidator.checkInputMoney(drink.drinkPrice(), totalPrice);

        int changes = orderCalculator.calculateChanges(totalPrice, drink.drinkPrice());


        changeValidator.validInputMoney(changes, orderTarget.vendingMachineId()); //거스름돈를 줄 수 있는지 검사
        orderSaver.saveOrder(drink.drinkInfo().drinkId(), vendingMachine.vendingMachineId());

        stockUpdater.updateRemoveAmount(
            stockReader.getStock(
                orderTarget.drinkId(), orderTarget.vendingMachineId()
            )
        );
        return changeProvider.provide(changes, orderTarget.vendingMachineId());
    }

    /** NOTE
     * 각 자판기 각 음료별 일별/월별 매출
     */
    public List<MonthlyStatisticResponse> statisticsSales(
        Long vendingMachineId,
        DrinkInfo drinkInfo
    ) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        DateTimeFormatter monthFormatter = getMonthFormatter();

        List<OrderResponse> orderList = drinkInfo.drinkId() == 0L
            ? orderReader.getOrderListToVendingMachine(vendingMachineId)
            : orderReader.getOrderListToDrink(vendingMachineId, drinkInfo.drinkId());

        List<DailyStatisticsResponse> dailyStatisticResponses =
            statisticsCalculator.getDailyStatisticsList(orderList, dateTimeFormatter);

        return getStatistics(monthFormatter, dailyStatisticResponses);
    }

    /** NOTE
     * 월별 매출 구하는 로직
     */
    private List<MonthlyStatisticResponse> getStatistics(
        DateTimeFormatter monthFormatter,
        List<DailyStatisticsResponse> dailyStatisticResponses
    ) {
        return statisticsCalculator.getMonthlyStatisticList(monthFormatter,
            dailyStatisticResponses);
    }
}
