package io.github.japangiserver.domains.admin.service.serviceimpl;

import io.github.japangiserver.domains.admin.persistence.repository.AdminRepository;
import io.github.japangiserver.domains.admin.Admin;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 관리자 유효성 검사 implement Layer
 */
@Component
@RequiredArgsConstructor
public class AdminValidator {

    private final AdminRepository adminRepository;

    /** NOTE <VARIABLE>
     * 비밀번호 최소 8글자, 특수문자 및 숫자 포함 정규식
     */
    private static final Pattern PASSWORD_PATTERN =
        Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

    /** NOTE
     * 비밀번호 형식 검증 구현체
     * @param password 검증할 비밀번호
     */
    public void validPassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalStateException("비밀번호 형식이 맞지 않습니다!");
        }
    }

    /** NOTE
     * 괸리자 계정 유무 확인 구현체
     * @param username 검사할 관리자 이름
     */
    @Transactional(readOnly = true)
    public void checkUsername(String username) {
        if (!adminRepository.existsByUsername(username)) {
            throw new IllegalStateException("존재하지 않는 회원입니다!");
        }
    }

    /** NOTE
     * 입력한 비밀번호가 일치하는지 확인하는 구현체
     * @param password 검증할 비밀번호
     */
    @Transactional(readOnly = true)
    public void checkPassword(String password) {
        if (!adminRepository.existsByPassword(password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다!");
        }
    }

    /** NOTE
     * 로그인 구현체
     * @param username 관리자 이름
     * @param password 관리자 비밀번호
     */
    @Transactional(readOnly = true)
    public void checkLogin(String username, String password) {
        checkUsername(username);
        checkPassword(password);
    }

    /** NOTE
     * 로그인 후 관리자 domain 변환 구현체
     * @param admin admin domain
     */
    @Transactional(readOnly = true)
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
