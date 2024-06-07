package io.github.japangiserver.domains.admin.serviceimpl;

import io.github.japangiserver.domains.admin.AdminEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUpdater {
    private final AdminReader adminReader;
    public void updatePassword(Long adminId, String password) {
        AdminEntity adminEntity = adminReader.findAdmin(adminId);
        adminEntity.updatePassword(password);
    }
}
