package io.github.japangiserver.domains.admin.service.serviceimpl;

import static io.github.japangiserver.util.mapper.admin.AdminEntityMapper.toEntityFromDomain;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminCreator {
    private final AdminRepository adminRepository;

    @Transactional
    public Long createAdminEntity(Admin admin) {
        AdminEntity adminEntity = toEntityFromDomain(admin);
        return saveAdmin(adminEntity);
    }

    @Transactional
    public Long saveAdmin(AdminEntity adminEntity){
        return adminRepository.save(adminEntity).getAdminId();
    }

}
