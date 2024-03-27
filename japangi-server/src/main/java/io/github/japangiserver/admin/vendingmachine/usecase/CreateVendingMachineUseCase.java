package io.github.japangiserver.admin.vendingmachine.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.response.IdResponse;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.usecase.BaseUseCase;
import io.github.japangiserver.domain.vendingmachine.VendingMachineService;
import org.springframework.stereotype.Component;

@Component
public class CreateVendingMachineUseCase extends BaseUseCase<Object> {

    private final VendingMachineService vendingMachineService;

    public CreateVendingMachineUseCase(
            ObjectMapper objectMapper,
            VendingMachineService vendingMachineService
    ) {
        super(objectMapper);
        this.vendingMachineService = vendingMachineService;
    }

    @Override
    public SocketResponse core(Object data) {
        long vendingMachineId = vendingMachineService.init();
        return SocketResponse.success(new IdResponse(vendingMachineId));
    }

    @Override
    public Class<Object> support() {
        return Object.class;
    }
}
