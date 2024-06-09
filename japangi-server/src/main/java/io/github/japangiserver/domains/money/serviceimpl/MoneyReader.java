package io.github.japangiserver.domains.money.serviceimpl;

import io.github.japangiserver.domains.money.persistence.entity.MoneyEntity;
import io.github.japangiserver.domains.money.persistence.repository.MoneyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MoneyReader {
    private final MoneyRepository moneyRepository;

    @Transactional(readOnly = true)
    public Long getMoney(int value) {
        return moneyRepository.findByValue(value)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 화폐단위입니다!")).getMoneyId();
    }

    @Transactional(readOnly = true)
    public List<MoneyEntity> getMoneyList(){
        return moneyRepository.findAll();
    }
}
