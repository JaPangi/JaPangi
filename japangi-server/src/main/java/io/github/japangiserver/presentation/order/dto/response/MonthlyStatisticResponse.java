package io.github.japangiserver.presentation.order.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record MonthlyStatisticResponse(
    List<DailyStatisticsResponse> dailyStatisticResponses, //일별
    String month,
    int totalPrice, //달마다 팔린 금액 합산
    int totalAmount //달마다 팔린 수량
) {

}
