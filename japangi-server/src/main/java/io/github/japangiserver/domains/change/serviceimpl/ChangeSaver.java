package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import io.github.japangiserver.domains.change.persistence.repository.ChangeRepository;
import io.github.japangiserver.domains.money.serviceimpl.MoneyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangeSaver {
    private final ChangeRepository changeRepository;
    private final ChangeCreator changeCreator;
    private final MoneyReader moneyReader;
    @Transactional
    public void initChange(Long vendingMachineId){
        moneyReader.getMoneyList()
            .forEach(money -> {
                ChangeEntity change = changeCreator.createChange(vendingMachineId, money);
                changeRepository.save(change);
            });
    }
}
