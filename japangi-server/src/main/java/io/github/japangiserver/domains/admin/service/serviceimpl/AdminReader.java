package io.github.japangiserver.domains.admin.service.serviceimpl;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.AdminPassword;
import io.github.japangiserver.domains.admin.persistence.entity.Authority;
import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminReader {
    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public Admin findAdmin(Long adminId){
        return adminRepository.findById(adminId)
            .map(admin -> new Admin(
                admin.getUsername(),
                new AdminPassword(admin.getPassword()),
                Authority.ROLE_ADMIN
            ))
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자입니다!"));
    }
}
