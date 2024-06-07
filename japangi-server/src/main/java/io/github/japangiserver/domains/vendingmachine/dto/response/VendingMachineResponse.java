package io.github.japangiserver.domains.vendingmachine.dto.response;

import io.github.japangiserver.domains.vendingmachine.VendingMachineEntity;
import lombok.Builder;

@Builder
public record VendingMachineResponse(long vendingMachineId){
    public static VendingMachineResponse fromEntity(VendingMachineEntity vendingMachineEntity){
        return VendingMachineResponse.builder()
            .vendingMachineId(vendingMachineEntity.getVendingMachineId())
            .build();
    }
}
