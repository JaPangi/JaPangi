package io.github.japangiserver.presentation.drink;

import io.github.japangiserver.domains.drink.service.DrinkService;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.KeyParameter;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

@SocketController
@RequestMapping(key = "DRINK")
@RequiredArgsConstructor
public class DrinkController {
    private final DrinkService drinkService;

    /** NOTE
     * 음료 세부사항 정보 확인(가격, 이름)
     */
    @RequestMapping(key = "GET_{drinkId}")
    public SocketResponse findDrinkInfo(@KeyParameter Long drinkId){
        return SocketResponse.success(drinkService.showDrinkInfo(drinkId));
    }
}
