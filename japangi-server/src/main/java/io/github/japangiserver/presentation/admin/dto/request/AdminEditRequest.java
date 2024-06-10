package io.github.japangiserver.presentation.admin.dto.request;

import io.github.japangiserver.domains.admin.AdminPassword;
import io.github.japangiserver.domains.admin.NewPassword;

public record AdminEditRequest(String password, String changePassword) {
    public AdminPassword toAdminPassword(){
        return new AdminPassword(password);
    }
    public NewPassword toNewpassword(){
        return new NewPassword(changePassword);
    }
}
