package io.github.japangiserver.domains.admin.serviceimpl;

import io.github.japangiserver.domains.admin.AdminEntity;
import io.github.japangiserver.domains.admin.AdminRepository;
import io.github.japangiserver.domains.admin.Authority;
import io.github.japangiserver.domains.admin.domain.Admin;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminValidator {

    private final AdminRepository adminRepository;
    private static final Pattern PASSWORD_PATTERN =
        Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

    public void validPassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalStateException("비밀번호 형식이 맞지 않습니다!");
        }
    }

    public void checkUsername(String username) {
        if (!adminRepository.existsByUsername(username)) {
            throw new IllegalStateException("존재하지 않는 회원입니다!");
        }
    }

    public void checkPassword(String password) {
        if (!adminRepository.existsByPassword(password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다!");
        }
    }

    public void checkLogin(String username, String password) {
        checkUsername(username);
        checkPassword(password);
    }

    public String processLogin(Admin admin) {
        checkLogin(admin.username(), admin.adminPassword().password());
        Admin adminDomain = adminRepository.findByUsernameAndPassword(
                admin.username(),
                admin.adminPassword().password()
            )
            .map(Admin::fromEntity)
            .orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자입니다!"));
        return adminDomain.username();
    }
}
