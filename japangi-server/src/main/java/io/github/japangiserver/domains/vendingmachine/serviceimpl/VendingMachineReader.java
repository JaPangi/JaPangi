package io.github.japangiserver.domains.vendingmachine.serviceimpl;

import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.VendingMachineRepository;
import io.github.japangiserver.domains.vendingmachine.dto.response.VendingMachineResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendingMachineReader {
    private final VendingMachineRepository vendingMachineRepository;

    public VendingMachineEntity getVendingMachine(Long vendingMachineId){
        return vendingMachineRepository.findById(vendingMachineId)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 자판기입니다"));
    }

    public VendingMachineResponse getVendingMachineResponse(Long vendingMachineId){
        VendingMachineEntity vendingMachineEntity = getVendingMachine(vendingMachineId);
        return VendingMachineResponse.builder()
            .vendingMachineId(vendingMachineEntity.getVendingMachineId())
            .build();
    }

    public List<VendingMachineResponse> vendingMachineList(){
        return vendingMachineRepository.findAll()
            .stream()
            .map(VendingMachineResponse::fromEntity)
            .toList();
    }
}
