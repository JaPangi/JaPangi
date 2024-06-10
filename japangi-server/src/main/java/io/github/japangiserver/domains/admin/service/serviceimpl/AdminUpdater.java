package io.github.japangiserver.domains.admin.service.serviceimpl;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 관리자 수정 implement Layer
 */
@Component
@RequiredArgsConstructor
public class AdminUpdater {
    private final AdminReader adminReader;
    private final AdminRepository adminRepository;

    /** NOTE
     * 관리자 비말번호 수정 구현체
     * @param adminId 수정하려는 관리자 Id(PK)
     * @param password 수정할 비밀번호
     */
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
