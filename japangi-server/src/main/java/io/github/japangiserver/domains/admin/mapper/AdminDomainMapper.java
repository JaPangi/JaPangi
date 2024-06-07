package io.github.japangiserver.domains.admin.mapper;

import io.github.japangiserver.domains.admin.Authority;
import io.github.japangiserver.domains.admin.domain.Admin;
import io.github.japangiserver.domains.admin.domain.AdminPassword;
import io.github.japangiserver.domains.admin.dto.request.AdminEdit;
import io.github.japangiserver.domains.admin.dto.request.AdminAccount;
import org.springframework.stereotype.Component;

@Component
public class AdminDomainMapper {
    public Admin toDomain(AdminAccount dto) {
        return new Admin(
            dto.username(),
            new AdminPassword(dto.password()),
            Authority.ROLE_ADMIN
        );
    }

    public AdminPassword toAdminPasswordDomain(AdminEdit adminEdit){
        return new AdminPassword(adminEdit.password());
    }
}
