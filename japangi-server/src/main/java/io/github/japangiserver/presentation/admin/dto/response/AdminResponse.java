package io.github.japangiserver.presentation.admin.dto.response;

import lombok.Builder;

@Builder
public record AdminResponse(long adminId, String username) {

}
