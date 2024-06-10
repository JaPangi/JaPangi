package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.ChangeEntityReader;
import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 거스름돈 계산 implement Layer
 */
@Component
@RequiredArgsConstructor
public class ChangeCalculator {
    private final ChangeEntityReader changeEntityReader;

    /** NOTE
     * 거스름돈 감소 구현체
     * @param change change domain
     * @param changeAmount 감소할 거스름돈 양
     */
    @Transactional
    public void decreaseChange(Change change, int changeAmount) {
        ChangeEntity changeEntity = changeEntityReader.getChangeEntity(change);
        changeEntity.remainChange(changeAmount);
    }

    @Transactional
    public int calculatorChange(Change change, int changeAmount, int excess, int value) {
        decreaseChange(change,changeAmount);
        excess -= value*changeAmount;
        return excess;
    }
}
