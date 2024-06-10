package io.github.japangiserver.presentation.admin.dto.request;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.AdminPassword;
import io.github.japangiserver.domains.admin.persistence.entity.Authority;

public record AdminAccountRequest(String username, String password) {

    public Admin toAdmin(){
        return new Admin(
            0L,
            username,
            new AdminPassword(password),
            Authority.ROLE_ADMIN);
    }
}
