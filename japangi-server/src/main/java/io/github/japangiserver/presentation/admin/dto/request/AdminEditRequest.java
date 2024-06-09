package io.github.japangiserver.presentation.admin.dto.request;

import io.github.japangiserver.domains.admin.AdminPassword;

public record AdminEditRequest(String password) {
    public AdminPassword toAdminPassword(){
        return new AdminPassword(password);
    }
}
