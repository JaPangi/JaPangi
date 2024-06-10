package io.github.japangiserver.domains.admin.service.serviceimpl;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.AdminPassword;
import io.github.japangiserver.domains.admin.persistence.entity.Authority;
import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 관리자 조회 implement Layer
 */
@Component
@RequiredArgsConstructor
public class AdminReader {
    private final AdminRepository adminRepository;

    /** NOTE
     * 관리자 domain 조회 구현체
     * @param adminId 조회하려는 관리자 Id(PK)
     */
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
