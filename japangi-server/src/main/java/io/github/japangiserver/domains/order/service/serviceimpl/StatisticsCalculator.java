package io.github.japangiserver.domains.order.service.serviceimpl;

import io.github.japangiserver.presentation.order.dto.response.DailyStatisticsResponse;
import io.github.japangiserver.presentation.order.dto.response.MonthlyStatisticResponse;
import io.github.japangiserver.presentation.order.dto.response.OrderResponse;
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
    public List<DailyStatisticsResponse> getDailyStatisticsList(
        List<OrderResponse> orderList,
        DateTimeFormatter dateTimeFormatter
    ) {
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
    private DailyStatisticsResponse getDailyStatistics(
        List<OrderResponse> dailyOrder,
        DateTimeFormatter dateTimeFormatter
    ) {
        return DailyStatisticsResponse.builder()
            .date(dailyOrder.get(0).orderAt().format(dateTimeFormatter))
            .totalPrice(
                dailyOrder.stream()
                    .mapToInt(OrderResponse::drinkPrice)
                    .sum()
            )
            .totalAmount(dailyOrder.size())
            .build();
    }

    public MonthlyStatisticResponse getMonthlyStatistic(List<DailyStatisticsResponse> monthOrder) {
        return MonthlyStatisticResponse.builder()
            .dailyStatisticResponses(monthOrder)
            .month((monthOrder.get(0).date().substring(0, 7)))
            .totalPrice(monthOrder.stream().mapToInt(DailyStatisticsResponse::totalPrice).sum())
            .totalAmount(
                monthOrder.stream().mapToInt(DailyStatisticsResponse::totalAmount).sum())
            .build();
    }

    public List<MonthlyStatisticResponse> getMonthlyStatisticList(DateTimeFormatter monthFormatter,
        List<DailyStatisticsResponse> dailyStatisticResponses) {
        return dailyStatisticResponses.stream()
            .collect(Collectors.groupingBy(
                    daily -> LocalDate.parse(daily.date()).format(monthFormatter),
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        this::getMonthlyStatistic
                    )
                )
            ).values().stream()
            .sorted(Comparator.comparing(MonthlyStatisticResponse::month))
            .toList();
    }
}
