package io.github.japangiserver.domains.drink;

import io.github.japangiserver.domains.drink.serviceimpl.DrinkReader;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.KeyParameter;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

@SocketController
@RequestMapping(key = "DRINK")
@RequiredArgsConstructor
public class DrinkController {
    private final DrinkReader drinkReader;

    /** NOTE
     * 음료 세부사항 정보 확인(가격, 이름)
     */
    @RequestMapping(key = "GET_{drinkId}")
    public SocketResponse getDrinkInfo(@KeyParameter Long drinkId){
        return SocketResponse.success(drinkReader.showDrinkInfo(drinkId));
    }
}
