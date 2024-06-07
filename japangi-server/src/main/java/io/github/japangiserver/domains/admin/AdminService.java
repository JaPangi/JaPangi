package io.github.japangiserver.domains.admin;

import io.github.japangiserver.domains.admin.domain.Admin;
import io.github.japangiserver.domains.admin.domain.AdminPassword;
import io.github.japangiserver.domains.admin.dto.request.AdminEdit;
import io.github.japangiserver.domains.admin.dto.request.AdminRequest;
import io.github.japangiserver.domains.admin.dto.request.AdminAccount;
import io.github.japangiserver.domains.admin.mapper.AdminDomainMapper;
import io.github.japangiserver.domains.admin.serviceimpl.AdminCreator;
import io.github.japangiserver.domains.admin.serviceimpl.AdminUpdater;
import io.github.japangiserver.domains.admin.serviceimpl.AdminValidator;
import io.github.japangiserver.domains.change.serviceimpl.ChangeUpdater;
import io.github.japangiserver.domains.drink.dto.response.DrinkResponse;
import io.github.japangiserver.domains.drink.serviceimpl.DrinkReader;
import io.github.japangiserver.domains.drink.serviceimpl.DrinkUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminValidator adminValidator;
    private final DrinkReader drinkReader;
    private final AdminCreator adminCreator;
    private final AdminUpdater adminUpdater;
    private final ChangeUpdater changeUpdater;
    private final DrinkUpdater drinkUpdater;
    private final AdminDomainMapper adminDomainMapper;
    @Transactional
    public Long createAdmin(AdminAccount adminAccount) {
        Admin admin = adminDomainMapper.toDomain(adminAccount);
        adminValidator.validPassword(admin.adminPassword().password());
        return adminCreator.createAdmin(admin);
    }

    @Transactional
    public Long editAdmin(Long adminId, AdminEdit adminEdit) {
        AdminPassword adminPassword = adminDomainMapper.toAdminPasswordDomain(adminEdit);
        adminValidator.validPassword(adminPassword.password());
        adminUpdater.updatePassword(adminId,adminPassword.password());
        return adminId;
    }

    @Transactional
    public Long editVendingMachine(Long drinkId, AdminRequest adminRequest) {
        DrinkResponse drink = drinkReader.getDrinkResponse(drinkId);
        drinkUpdater.modifyDrink(adminRequest.drinkName(), adminRequest.drinkPrice(),drink.drinkId());
        return drinkId;
    }

    @Transactional
    public Long collectBills(Long vendingMachineId) {
        changeUpdater.collectChange(vendingMachineId);
        return vendingMachineId;
    }

    @Transactional(readOnly = true)
    public String loginAdmin(AdminAccount adminAccount) {
        Admin admin = adminDomainMapper.toDomain(adminAccount);
        return adminValidator.processLogin(admin);
    }
}
