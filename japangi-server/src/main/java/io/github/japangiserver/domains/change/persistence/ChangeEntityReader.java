package io.github.japangiserver.domains.change.persistence;

import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import io.github.japangiserver.domains.change.persistence.repository.ChangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeEntityReader {
    private final ChangeRepository changeRepository;
    public ChangeEntity getChangeEntity(Change change) {
        return changeRepository.findByVendingMachineIdAndMoneyId(
                change.vendingMachineId(),
                change.moneyId()
            )
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 거스름돈입니다!"));
    }
}
