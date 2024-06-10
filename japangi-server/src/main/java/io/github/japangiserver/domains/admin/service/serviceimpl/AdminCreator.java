package io.github.japangiserver.domains.admin.service.serviceimpl;

import static io.github.japangiserver.util.mapper.admin.AdminEntityMapper.toEntityFromDomain;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.persistence.entity.AdminEntity;
import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 관리자 생성 implement Layer
 */
@Component
@RequiredArgsConstructor
public class AdminCreator {
    private final AdminRepository adminRepository;

    /** NOTE
     * 관리자 회원가입 구현체
     * @param admin admin domain
     */
    @Transactional
    public Long createAdminEntity(Admin admin) {
        AdminEntity adminEntity = toEntityFromDomain(admin);
        return saveAdmin(adminEntity);
    }

    /** NOTE
     * 관리자 entity 저장 구현체
     * @param adminEntity 관리자 entity
     */
    @Transactional
    public Long saveAdmin(AdminEntity adminEntity){
        return adminRepository.save(adminEntity).getAdminId();
    }

}
