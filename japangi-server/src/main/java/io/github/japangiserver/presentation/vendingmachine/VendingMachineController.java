package io.github.japangiserver.presentation.vendingmachine;

import io.github.japangiserver.domains.vendingmachine.service.VendingMachineService;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.KeyParameter;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

@SocketController
@RequestMapping(key = "VENDING_MACHINE")
@RequiredArgsConstructor
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    /**
     * NOTE
     * 자판기 목록 불러오는 API
     */
    @RequestMapping(key = "ALL")
    public SocketResponse getVendingMachineList() {
        return SocketResponse.success(vendingMachineService.showVendingMachines());
    }

    /**
     * NOTE
     * 자판기 초기셋팅 API
     */
    @RequestMapping(key = "INIT")
    public SocketResponse initVendingMachine() {
        return SocketResponse.success(vendingMachineService.init());
    }

    /**
     * NOTE
     * 자판기 재고 현황 파악
     */
    @RequestMapping(key = "GET_{vendingMachineId}")
    public SocketResponse getVendingMachineStockStatus(@KeyParameter Long vendingMachineId) {
        return SocketResponse.success(
            vendingMachineService.showCurrentStockStatus(vendingMachineId));
    }
}