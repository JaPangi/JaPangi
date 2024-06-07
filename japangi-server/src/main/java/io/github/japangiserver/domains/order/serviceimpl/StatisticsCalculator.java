package io.github.japangiserver.domains.order.serviceimpl;

import io.github.japangiserver.domains.order.dto.response.DailyStatistics;
import io.github.japangiserver.domains.order.dto.response.MonthlyStatistic;
import io.github.japangiserver.domains.order.dto.response.OrderResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StatisticsCalculator {

    /** NOTE
     * 일별 매출 리스트 로직
     */
    public List<DailyStatistics> getDailyStatisticsList(List<OrderResponse> orderList,
        DateTimeFormatter dateTimeFormatter) {
        return orderList
            .stream()
            .collect(Collectors.groupingBy(
                order -> order.orderAt().format(dateTimeFormatter),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    dailyOrder -> getDailyStatistics(dailyOrder, dateTimeFormatter)
                )
            ))
            .values().stream()
            .toList();
    }

    /** NOTE
     * 일별 매출 구하는 로직
     */
    private DailyStatistics getDailyStatistics(
        List<OrderResponse> dailyOrder,
        DateTimeFormatter dateTimeFormatter
    ) {
        return DailyStatistics.builder()
            .date(dailyOrder.get(0).orderAt().format(dateTimeFormatter))
            .totalPrice(
                dailyOrder.stream()
                    .mapToInt(OrderResponse::drinkPrice)
                    .sum()
            )
            .totalAmount(dailyOrder.size())
            .build();
    }

    public MonthlyStatistic getMonthlyStatistic(List<DailyStatistics> monthOrder) {
        return MonthlyStatistic.builder()
            .dailyStatistics(monthOrder)
            .month((monthOrder.get(0).date().substring(0, 7)))
            .totalPrice(monthOrder.stream().mapToInt(DailyStatistics::totalPrice).sum())
            .totalAmount(
                monthOrder.stream().mapToInt(DailyStatistics::totalAmount).sum())
            .build();
    }

    public List<MonthlyStatistic> getMonthlyStatisticList(DateTimeFormatter monthFormatter,
        List<DailyStatistics> dailyStatistics) {
        return dailyStatistics.stream()
            .collect(Collectors.groupingBy(
                    daily -> LocalDate.parse(daily.date()).format(monthFormatter),
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        this::getMonthlyStatistic
                    )
                )
            ).values().stream()
            .sorted(Comparator.comparing(MonthlyStatistic::month))
            .toList();
    }
}
