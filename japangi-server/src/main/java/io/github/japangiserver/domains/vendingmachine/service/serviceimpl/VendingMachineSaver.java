package io.github.japangiserver.domains.vendingmachine.service.serviceimpl;

import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.repository.VendingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VendingMachineSaver {
    private final VendingMachineRepository vendingMachineRepository;

    @Transactional
    public Long saveVendingMachine(){
        VendingMachineEntity vendingMachineEntity = new VendingMachineEntity();
        return vendingMachineRepository.save(vendingMachineEntity).getVendingMachineId();
    }
}
