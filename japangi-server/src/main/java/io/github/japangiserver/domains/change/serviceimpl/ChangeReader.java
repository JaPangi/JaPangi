package io.github.japangiserver.domains.change.serviceimpl;

import io.github.japangiserver.domains.change.Change;
import io.github.japangiserver.domains.change.persistence.repository.ChangeRepository;
import io.github.japangiserver.presentation.change.dto.response.ChangeStatusResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * Change 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class ChangeReader {

    private final ChangeRepository changeRepository;

    /** NOTE
     * 거스름돈 단위 조회 구현체
     * @param change change domain
     */
    public int getValue(Change change){
        return change.value();
    }

    /** NOTE
     * List<Change> domain으로 변환하는 구현체
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional(readOnly = true)
    public List<Change> getChanges(Long vendingMachineId){
        return changeRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(Change::toChange)
            .toList();
    }

    /** NOTE
     * Change domain으로 변환하는 구현체
     * @param vendingMachineId 자판기 Id(PK)
     * @param moneyId 화폐 Id(PK)
     */
    @Transactional(readOnly = true)
    public Change getChange(Long vendingMachineId, Long moneyId) {
        return changeRepository.findByVendingMachineIdAndMoneyId(vendingMachineId, moneyId)
            .map(Change::toChange)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 거스름돈 단위입니다!"));
    }

    /** NOTE .TODO -> domain 으로 변환해서 반환
     * 화페 현황 반환 구현체
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional(readOnly = true)
    public List<ChangeStatusResponse> getCurrentStatus(Long vendingMachineId){
        return changeRepository.findAllByVendingMachineId(vendingMachineId)
            .stream()
            .map(ChangeStatusResponse::fromEntity)
            .toList();
    }
}
