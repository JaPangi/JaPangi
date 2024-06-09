package io.github.japangiserver.domains.admin.service.serviceimpl;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminUpdater {
    private final AdminReader adminReader;
    private final AdminRepository adminRepository;
    @Transactional
    public void updatePassword(Long adminId, String password) {
        Admin admin = adminReader.findAdmin(adminId);
        AdminEntity adminEntity = adminRepository.findByUsernameAndPassword(
            admin.username(), admin.adminPassword().password()
            )
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자입니다!"));
        adminEntity.updatePassword(password);
    }
}
