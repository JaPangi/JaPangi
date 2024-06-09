package io.github.japangiserver.presentation.change.dto.response;

import io.github.japangiserver.domains.change.persistence.entity.ChangeEntity;
import lombok.Builder;

@Builder
public record ChangeStatusResponse(int amount, int value) {

    public static ChangeStatusResponse fromEntity(ChangeEntity changeEntity){
        return ChangeStatusResponse.builder()
            .amount(changeEntity.getAmount())
            .value(changeEntity.getMoneyEntity().getValue())
            .build();
    }
}
