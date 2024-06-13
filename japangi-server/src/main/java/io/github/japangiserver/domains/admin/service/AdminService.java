package io.github.japangiserver.domains.admin.service;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.AdminPassword;
import io.github.japangiserver.domains.admin.NewPassword;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminCreator;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminReader;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminUpdater;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminValidator;
import io.github.japangiserver.domains.change.serviceimpl.ChangeUpdater;
import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkUpdater;
import io.github.japangiserver.presentation.admin.dto.response.AdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** NOTE
 * 관리자 핵심 비즈니스 로직 service Layer
 */
@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminValidator adminValidator;
    private final AdminCreator adminCreator;
    private final AdminUpdater adminUpdater;
    private final ChangeUpdater changeUpdater;
    private final DrinkUpdater drinkUpdater;

    /** NOTE
     * 관리자 회원가입 서비스
     * @param admin admin domain
     */
    public Long createAdmin(Admin admin) {
        adminValidator.validPassword(admin.adminPassword().password());
        return adminCreator.createAdminEntity(admin);
    }

    /** NOTE
     * 관리자 비밀번호 수정 서비스
     * @param adminId 변경할 관리자 Id(PK)
     * @param adminPassword  기존 비밀번호
     * @param newPassword 변경할 비밀번호
     */
    public Long editAdmin(
        Long adminId,
        AdminPassword adminPassword,
        NewPassword newPassword
    ) {
        System.out.println(adminId + adminPassword.password());
        adminValidator.findAdminAccount(adminId,adminPassword.password());
        adminValidator.validPassword(newPassword.newPassword());
        adminUpdater.updatePassword(adminId,newPassword.newPassword());
        return adminId;
    }

    /** NOTE
     * 자판기의 음료 가격, 이름 수정 서비스
     * @param drink drink domain
     */
    public Long editVendingMachine(Drink drink) {
        drinkUpdater.modifyDrink(drink);
        return drink.drinkInfo().drinkId();
    }

    /** NOTE
     * 관리자 수금 서비스
     * @param vendingMachineId 수금하려는 자판기Id(PK)
     */
    public int collectBills(Long vendingMachineId) {
        return changeUpdater.collectChange(vendingMachineId);
    }

    /** NOTE
     * 관리자 로그인 서비스
     * @param admin admin domain
     */
    public AdminResponse loginAdmin(Admin admin) {
        Admin adminDomain = adminValidator.processLogin(admin);
        return AdminResponse.builder()
            .adminId(adminDomain.adminId())
            .username(adminDomain.username())
            .build();
    }
}
