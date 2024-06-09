package io.github.japangiserver.domains.vendingmachine.service.serviceimpl;

import io.github.japangiserver.domains.vendingmachine.VendingMachine;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.repository.VendingMachineRepository;
import io.github.japangiserver.presentation.vendingmachine.dto.response.VendingMachineResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VendingMachineReader {
    private final VendingMachineRepository vendingMachineRepository;

  /*  @Transactional(readOnly = true)
    public VendingMachineEntity getVendingMachineEntity(Long vendingMachineId){
        return vendingMachineRepository.findById(vendingMachineId)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다"));
    }
*/
    @Transactional(readOnly = true)
    public VendingMachine getVendingMachine(Long vendingMachineId){
        return vendingMachineRepository.findById(vendingMachineId)
            .map(vendingMachine -> new VendingMachine(vendingMachineId))
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다"));
    }

    @Transactional(readOnly = true)
    public List<VendingMachineResponse> vendingMachineList(){
        return vendingMachineRepository.findAll()
            .stream()
            .map(VendingMachineResponse::fromEntity)
            .toList();
    }
}
