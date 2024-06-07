package io.github.japangiserver.domains.vendingmachine.serviceimpl;

import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import io.github.japangiserver.domains.vendingmachine.VendingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendingMachineSaver {
    private final VendingMachineRepository vendingMachineRepository;

    public Long saveVendingMachine(VendingMachineEntity vendingMachine){
        return vendingMachineRepository.save(vendingMachine).getVendingMachineId();
    }
}
