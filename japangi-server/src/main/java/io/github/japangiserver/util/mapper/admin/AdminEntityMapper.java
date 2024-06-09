package io.github.japangiserver.util.mapper.admin;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;

public class AdminEntityMapper {
    public static AdminEntity toEntityFromDomain(Admin domain) {
        return AdminEntity.builder()
            .username(domain.username())
            .password(domain.adminPassword().password())
            .authority(domain.authority())
            .build();
    }
}
