package io.github.japangiserver.domains.admin.serviceimpl;

import io.github.japangiserver.domains.admin.AdminEntity;
import io.github.japangiserver.domains.admin.AdminRepository;
import io.github.japangiserver.domains.admin.domain.Admin;
import io.github.japangiserver.domains.admin.mapper.AdminEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCreator {
    private final AdminRepository adminRepository;
    private final AdminEntityMapper entityMapper;

    public Long createAdmin(Admin admin) {
        AdminEntity adminEntity = entityMapper.toEntityFromDomain(admin);
        return saveAdmin(adminEntity);
    }

    public Long saveAdmin(AdminEntity adminEntity){
        return adminRepository.save(adminEntity).getAdminId();
    }

}
