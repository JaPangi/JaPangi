package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.ChangeEntity;
import io.github.japangiserver.domains.change.ChangeRepository;
import io.github.japangiserver.domains.money.serviceimpl.MoneyReader;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeSaver {
    private final ChangeRepository changeRepository;
    private final ChangeCreator changeCreator;
    private final MoneyReader moneyReader;
    public void initChange(VendingMachineEntity vendingMachine){
        moneyReader.getMoneyList()
            .forEach(money -> {
                ChangeEntity change = changeCreator.createChange(vendingMachine, money);
                changeRepository.save(change);
            });
    }
}
