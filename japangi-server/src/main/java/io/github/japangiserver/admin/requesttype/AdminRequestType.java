package io.github.japangiserver.admin.requesttype;

import io.github.japangiserver.base.requesttype.RequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminRequestType implements RequestType {

    CREATE_VENDING_MACHINE("ADMIN_CREATE_VENDING_MACHINE", "createVendingMachineUseCase");

    private final String name;
    private final String useCaseName;
}
