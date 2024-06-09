package io.github.japangiserver.domains.admin.service;

import io.github.japangiserver.domains.admin.Admin;
import io.github.japangiserver.domains.admin.AdminPassword;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminCreator;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminUpdater;
import io.github.japangiserver.domains.admin.service.serviceimpl.AdminValidator;
import io.github.japangiserver.domains.change.serviceimpl.ChangeUpdater;
import io.github.japangiserver.domains.drink.Drink;
import io.github.japangiserver.domains.drink.service.serviceimpl.DrinkUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminValidator adminValidator;
    private final AdminCreator adminCreator;
    private final AdminUpdater adminUpdater;
    private final ChangeUpdater changeUpdater;
    private final DrinkUpdater drinkUpdater;

    public Long createAdmin(Admin admin) {
        adminValidator.validPassword(admin.adminPassword().password());
        return adminCreator.createAdminEntity(admin);
    }

    public Long editAdmin(Long adminId, AdminPassword adminPassword) {
        adminValidator.validPassword(adminPassword.password());
        adminUpdater.updatePassword(adminId,adminPassword.password());
        return adminId;
    }

    public Long editVendingMachine(Drink drink) {
        drinkUpdater.modifyDrink(drink);
        return drink.drinkInfo().drinkId();
    }

    public Long collectBills(Long vendingMachineId) {
        changeUpdater.collectChange(vendingMachineId);
        return vendingMachineId;
    }

    public String loginAdmin(Admin admin) {
        return adminValidator.processLogin(admin);
    }
}
