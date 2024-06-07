package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.ChangeEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeValidator {

    private final ChangeReader changeReader;

    /**
     * NOTE
     * 거스름돈을 줄 수 있는지 검사
     * @param balance          줘야할 거스름돈
     * @param vendingMachineId 자판기 ID
     */
    public void validInputMoney(int balance, Long vendingMachineId){
        List<ChangeEntity> changeEntityList = changeReader.getChange(vendingMachineId); //자판기 별 거스름돈 현황
        if (!isProvide(balance, changeEntityList)){
        throw new IllegalStateException("돌려줄 잔액이 부족합니다!");
        }
    }

    public boolean isProvide(int balance, List<ChangeEntity> changeEntityList) {
        for (ChangeEntity changes : changeEntityList) {
            int value = changes.getMoneyEntity().getValue(); // 화폐 단위 예: 1000원
            int amount = changes.getAmount(); // 해당 화폐 단위의 개수 예: 5개

            while (balance >= value && amount > 0) {
                balance -= value;
                amount--;
            }
        }
        return balance == 0;
    }

    public void checkChanges(ChangeEntity changeEntity, int changeAmount){
        changeEntity.validChanges(changeAmount);
    }
}
