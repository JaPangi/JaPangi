package io.github.japangiserver.domains.change.dto.response;

import io.github.japangiserver.domains.change.ChangeEntity;
import lombok.Builder;

@Builder
public record ChangeResponse(int amount) {

    public static ChangeResponse fromEntity(ChangeEntity changeEntity){
        return ChangeResponse.builder()
            .amount(changeEntity.getAmount())
            .build();
    }
}
