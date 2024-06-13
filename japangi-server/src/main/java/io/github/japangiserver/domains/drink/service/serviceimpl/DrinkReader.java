package io.github.japangiserver.domains.drink.service.serviceimpl;

import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.DrinkInfo;
import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import io.github.japangiserver.domains.drink.persistence.repository.DrinkRepository;
import io.github.japangiserver.presentation.drink.dto.response.DrinkInfoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * drink 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class DrinkReader {
    private final DrinkRepository drinkRepository;

    /** NOTE
     * 음료 domain 생성 구현체
     * @param drinkId 음료Id(PK)
     */
    @Transactional(readOnly = true)
    public Drink getDrink(Long drinkId){
        return drinkRepository.findById(drinkId)
            .map(drink -> new Drink(
                new DrinkInfo(drinkId),
                drink.getDrinkName(),
                drink.getDrinkImageUrl(),
                drink.getDrinkPrice()
            ))
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 음료입니다!"));
    }

    /** NOTE
     * 음료 세부정보 조회 구현체
     * @param drinkId 음료Id(PK)
     */
    @Transactional(readOnly = true)
    public DrinkInfoResponse getDrinkInfo(Long drinkId) {
        Drink drink = getDrink(drinkId);
        return DrinkInfoResponse.builder()
            .drinkName(drink.drinkName())
            .imageUrl(drink.drinkImageUrl())
            .drinkPrice(drink.drinkPrice())
            .build();
    }

    /** NOTE
     * 음료 domain list 조회 구현체
     */
    @Transactional(readOnly = true)
    public List<Drink> getDrinkList(){
        return drinkRepository.findAll()
            .stream()
            .map(Drink::toDrink)
            .toList();
    }
}
