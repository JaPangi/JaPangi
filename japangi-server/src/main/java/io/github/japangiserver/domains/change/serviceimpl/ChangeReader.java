package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.ChangeEntity;
import io.github.japangiserver.domains.change.ChangeRepository;
import io.github.japangiserver.domains.change.dto.response.ChangeResponse;
import io.github.japangiserver.domains.change.dto.response.ChangeStatus;
import io.github.japangiserver.domains.money.MoneyEntity;
import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeReader {

    private final ChangeRepository changeRepository;
    public int getValue(ChangeEntity changeEntity){
        System.out.println(changeEntity.getChangeId());
        System.out.println(changeEntity.getMoneyEntity().getValue());
        return changeEntity.getMoneyEntity().getValue();
    }

    public List<ChangeEntity> getChange(Long vendingMachineId){
        return changeRepository.findAllByVendingMachineId(vendingMachineId);
    }
    public ChangeEntity getChangeEntity(VendingMachineEntity vendingMachineEntity, MoneyEntity moneyEntity) {
        return changeRepository.findByVendingMachineEntityAndMoneyEntity(vendingMachineEntity, moneyEntity)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 거스름돈 단위입니다!"));
    }
    public List<ChangeStatus> getCurrentStatus(Long vendingMachineId){
        return changeRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(ChangeStatus::fromEntity)
            .toList();
    }
}
