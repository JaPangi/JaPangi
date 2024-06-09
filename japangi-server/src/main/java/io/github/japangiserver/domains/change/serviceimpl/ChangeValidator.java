package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.ChangeEntityReader;
import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeValidator {

    private final ChangeReader changeReader;
    private final ChangeEntityReader changeEntityReader;
    /**
     * NOTE
     * 거스름돈을 줄 수 있는지 검사
     * @param balance          줘야할 거스름돈
     * @param vendingMachineId 자판기 ID
     */
    public void validInputMoney(int balance, Long vendingMachineId){
        List<Change> changeList = changeReader.getChanges(vendingMachineId); //자판기 별 거스름돈 현황
        if (!isProvided(balance, changeList)){
        throw new IllegalStateException("돌려줄 잔액이 부족합니다!");
        }
    }

    public boolean isProvided(int balance, List<Change> changeList) {
        for (Change changes : changeList) {
            int value = changes.value(); // 화폐 단위 예: 1000원
            int amount = changes.amount(); // 해당 화폐 단위의 개수 예: 5개

            while (balance >= value && amount > 0) {
                balance -= value;
                amount--;
            }
        }
        return balance == 0;
    }

    public void checkChanges(Change change, int changeAmount){
        ChangeEntity changeEntity = changeEntityReader.getChangeEntity(change);
        changeEntity.validChanges(changeAmount);
    }
}
