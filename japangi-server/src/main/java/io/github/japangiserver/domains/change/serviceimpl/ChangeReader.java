package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.repository.ChangeRepository;
import io.github.japangiserver.presentation.change.dto.response.ChangeStatusResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangeReader {

    private final ChangeRepository changeRepository;
    public int getValue(Change change){
        return change.value();
    }

    @Transactional(readOnly = true)
    public List<Change> getChanges(Long vendingMachineId){
        return changeRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(Change::toChange)
            .toList();
    }

    @Transactional(readOnly = true)
    public Change getChange(Long vendingMachineId, Long moneyId) {
        return changeRepository.findByVendingMachineIdAndMoneyId(vendingMachineId, moneyId)
            .map(Change::toChange)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 거스름돈 단위입니다!"));
    }

    @Transactional(readOnly = true)
    public List<ChangeStatusResponse> getCurrentStatus(Long vendingMachineId){
        return changeRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(ChangeStatusResponse::fromEntity)
            .toList();
    }
}
