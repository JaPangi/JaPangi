package io.github.japangiserver.domains.order.dto.response;

import io.github.japangiserver.domains.order.dto.response.DailyStatistics;
import java.util.List;
import lombok.Builder;

@Builder
public record MonthlyStatistic(
    List<DailyStatistics> dailyStatistics, //일별
    String month,
    int totalPrice, //달마다 팔린 금액 합산
    int totalAmount //달마다 팔린 수량
) {

}
