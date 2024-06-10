package io.github.japangiserver.domains.admin;

import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import io.github.japangiserver.domains.admin.persistence.entity.Authority;
import lombok.Builder;

/** NOTE
 * 관리자 정보 domain
 * @param username 관리자 ID
 * @param adminPassword 관리자 비밀번호
 * @param authority 관리자 권한
 */
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
