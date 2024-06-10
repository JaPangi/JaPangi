package io.github.japangiserver.domains.vendingmachine.service.serviceimpl;

import io.github.japangiserver.domains.vendingmachine.VendingMachine;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.repository.VendingMachineRepository;
import io.github.japangiserver.presentation.vendingmachine.dto.response.VendingMachineResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 자판기 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class VendingMachineReader {
    private final VendingMachineRepository vendingMachineRepository;

    /** NOTE
     * vendingMachine domain 조회 구현체
     * @param vendingMachineId 자판기 Id(PK)
     */
    @Transactional(readOnly = true)
    public VendingMachine getVendingMachine(Long vendingMachineId){
        return vendingMachineRepository.findById(vendingMachineId)
            .map(vendingMachine -> new VendingMachine(vendingMachineId))
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다"));
    }

    /** NOTE
     *  자판기 목록 조회 구현체
     */
    @Transactional(readOnly = true)
    public List<VendingMachineResponse> vendingMachineList(){
        return vendingMachineRepository.findAll()
            .stream()
            .map(VendingMachineResponse::fromEntity)
            .toList();
    }
}
