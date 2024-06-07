package io.github.japangiserver.domains.admin.serviceimpl;

import io.github.japangiserver.domains.admin.AdminEntity;
import io.github.japangiserver.domains.admin.AdminRepository;
import io.github.japangiserver.domains.admin.dto.response.AdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminReader {
    private final AdminRepository adminRepository;

    public AdminEntity findAdmin(Long adminId){
        return adminRepository.findById(adminId)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자입니다!"));
    }

    public AdminResponse getAdminResponse(Long adminId){
        return AdminResponse.builder()
            .adminId(adminId)
            .build();
    }
}
