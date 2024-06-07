package io.github.japangiserver.domains.drink.serviceimpl;

import io.github.japangiserver.domains.drink.DrinkEntity;
import io.github.japangiserver.domains.drink.dto.response.DrinkInfo;
import io.github.japangiserver.domains.drink.DrinkRepository;
import io.github.japangiserver.domains.drink.dto.response.DrinkResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrinkReader {
    private final DrinkRepository drinkRepository;

    public DrinkEntity getDrink(Long drinkId){
        return drinkRepository.findById(drinkId)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));
    }

    public DrinkResponse getDrinkResponse(Long drinkId){
        DrinkEntity drinkEntity = getDrink(drinkId);
        return DrinkResponse
            .builder()
            .drinkId(drinkEntity.getDrinkId())
            .drinkPrice(drinkEntity.getDrinkPrice())
            .build();
    }

    public DrinkInfo showDrinkInfo(Long drinkId) {
        DrinkEntity drinkEntity = getDrink(drinkId);
        return DrinkInfo.builder()
            .drinkName(drinkEntity.getDrinkName())
            .drinkPrice(drinkEntity.getDrinkPrice())
            .imageUrl(drinkEntity.getDrinkImageUrl())
            .build();
    }

    public List<DrinkEntity> getDrinkList(){
        return drinkRepository.findAll();
    }
}
