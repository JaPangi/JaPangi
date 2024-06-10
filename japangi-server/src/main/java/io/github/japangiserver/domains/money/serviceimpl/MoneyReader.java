package io.github.japangiserver.domains.money.serviceimpl;

import io.github.japangiserver.domains.money.persistence.entity.MoneyEntity;
import io.github.japangiserver.domains.money.persistence.repository.MoneyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * money domain 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class MoneyReader {
    private final MoneyRepository moneyRepository;

    /** NOTE
     *  화폐 조회 구현체
     * @param value 조회하려는 화페 단위
     */
    @Transactional(readOnly = true)
    public Long getMoney(int value) {
        return moneyRepository.findByValue(value)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 화폐단위입니다!")).getMoneyId();
    }

    /** NOTE
     * 화폐 List 조회 구현체
     */
    @Transactional(readOnly = true)
    public List<MoneyEntity> getMoneyList(){
        return moneyRepository.findAll();
    }
}
