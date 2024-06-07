package io.github.japangiserver.domains.change.dto.response;

import io.github.japangiserver.domains.change.ChangeEntity;
import lombok.Builder;

@Builder
public record ChangeStatus(int amount, int value) {

    public static ChangeStatus fromEntity(ChangeEntity changeEntity){
        return ChangeStatus.builder()
            .amount(changeEntity.getAmount())
            .value(changeEntity.getMoneyEntity().getValue())
            .build();
    }
}
