package io.github.japangiserver.util.mapper.admin;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;

/** NOTE
 * 관리자 entity mapper
 */
public class AdminEntityMapper {

    /** NOTE
     * domain -> entity 로 매핑
     * @param domain admin domain
     */
    public static AdminEntity toEntityFromDomain(Admin domain) {
        return AdminEntity.builder()
            .username(domain.username())
            .password(domain.adminPassword().password())
            .authority(domain.authority())
            .build();
    }
}
