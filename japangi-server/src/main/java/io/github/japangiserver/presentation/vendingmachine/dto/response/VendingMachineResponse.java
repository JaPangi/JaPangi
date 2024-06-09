package io.github.japangiserver.presentation.vendingmachine.dto.response;

import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import lombok.Builder;

@Builder
public record VendingMachineResponse(long vendingMachineId){
    public static VendingMachineResponse fromEntity(VendingMachineEntity vendingMachineEntity){
        return VendingMachineResponse.builder()
            .vendingMachineId(vendingMachineEntity.getVendingMachineId())
            .build();
    }
}
