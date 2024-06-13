package io.github.japangiserver.presentation.vendingmachine;

import io.github.japangiserver.domains.vendingmachine.service.VendingMachineService;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.KeyParameter;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

/**
 * NOTE
 * 자판기 기능 API
 */
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

    /** NOTE
     * 자판기 음료 재고 조회 API
     * @param vendingMachineId 자판기 Id(PK)
     */
    @RequestMapping(key = "INFO_{vendingMachineId}")
    public SocketResponse getVendingMachineInfo(@KeyParameter Long vendingMachineId){
        return SocketResponse.success(vendingMachineService.showVendingMachineInfo(vendingMachineId));
    }
}