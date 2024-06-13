package io.github.japangiserver.domains.drink.service;

import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkReader;
import io.github.japangiserver.presentation.drink.dto.response.DrinkInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** NOTE
 * 음료 핵심 비즈니스 로직 service Layer
 */
@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkReader drinkReader;

    /** NOTE
     * 음료 세부사항 정보 조회 서비스
     * @param drinkId 음료 Id(PK)
     */
    public DrinkInfoResponse showDrinkInfo(Long drinkId){
        return drinkReader.getDrinkInfo(drinkId);
    }
}
