package io.github.japangiserver.domains.admin.domain;

import io.github.japangiserver.domains.admin.AdminEntity;
import io.github.japangiserver.domains.admin.Authority;
import lombok.Builder;

@Builder
public record Admin(
    String username,
    AdminPassword adminPassword,
    Authority authority
) {
    public static Admin fromEntity(AdminEntity adminEntity){
        return new Admin(
            adminEntity.getUsername(),
            new AdminPassword(adminEntity.getPassword()),
            Authority.valueOf(String.valueOf(adminEntity.getAuthority()))
        );
    }
}
