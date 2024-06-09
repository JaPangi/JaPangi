package io.github.japangiserver.domains.drink.service;

import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkReader;
import io.github.japangiserver.presentation.drink.dto.response.DrinkInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkReader drinkReader;

    public DrinkInfoResponse showDrinkInfo(Long drinkId){
        return drinkReader.getDrinkInfo(drinkId);
    }
}
