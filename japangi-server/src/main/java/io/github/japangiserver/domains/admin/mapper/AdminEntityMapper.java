package io.github.japangiserver.domains.admin.mapper;

import io.github.japangiserver.domains.admin.AdminEntity;
import io.github.japangiserver.domains.admin.domain.Admin;
import io.github.japangiserver.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AdminEntityMapper implements Mapper<Admin,AdminEntity> {
    @Override
    public AdminEntity toEntityFromDomain(Admin domain) {
        return AdminEntity.builder()
            .username(domain.username())
            .password(domain.adminPassword().password())
            .authority(domain.authority())
            .build();
    }
}
