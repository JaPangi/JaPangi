package io.github.japangiserver.domains.money.serviceimpl;

import io.github.japangiserver.domains.money.MoneyEntity;
import io.github.japangiserver.domains.money.MoneyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoneyReader {
    private final MoneyRepository moneyRepository;

    public MoneyEntity getMoney(int value) {
        return moneyRepository.findByValue(value)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 화폐단위입니다!"));
    }

    public List<MoneyEntity> getMoneyList(){
        return moneyRepository.findAll();
    }
}
