package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.ChangeEntity;
import org.springframework.stereotype.Component;

@Component
public class ChangeCalculator {

    public void decreaseChange(ChangeEntity changeEntity, int changeAmount) {
        changeEntity.remainChange(changeAmount);
    }

    public int calculatorChange(ChangeEntity changeEntity, int changeAmount, int excess, int value) {
        decreaseChange(changeEntity,changeAmount);
        excess -= value*changeAmount;
        return excess;
    }
}
