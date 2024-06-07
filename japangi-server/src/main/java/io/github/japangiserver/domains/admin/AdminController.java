package io.github.japangiserver.domains.admin;

import io.github.japangiserver.domains.admin.dto.request.AdminEdit;
import io.github.japangiserver.domains.admin.dto.request.AdminRequest;
import io.github.japangiserver.domains.admin.dto.request.AdminAccount;
import io.github.japangiserver.domains.admin.dto.request.AdminStockRequest;
import io.github.japangiserver.domains.admin.dto.request.StatisticsRequest;
import io.github.japangiserver.domains.order.OrderService;
import io.github.japangiserver.domains.stock.application.StockService;
import io.github.japangiserver.domains.vendingmachine.VendingMachineService;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.KeyParameter;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestBody;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

@SocketController
@RequestMapping(key = "ADMIN")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final StockService stockService;
    private final VendingMachineService vendingMachineService;
    private final OrderService orderService;
    /** NOTE
     * 관리자 회원가입
     */
    @RequestMapping(key = "CREATE")
    public SocketResponse signUpAdmin(@RequestBody AdminAccount adminAccount){
        return SocketResponse.success(adminService.createAdmin(adminAccount));
    }

    /** NOTE
     * 관리자 비밀번호 변경
     */
    @RequestMapping(key = "PATCH_{adminId}")
    public SocketResponse modifyAdmin(
        @KeyParameter Long adminId,
        @RequestBody AdminEdit adminEdit
    ){
        return SocketResponse.success(adminService.editAdmin(adminId,adminEdit));
    }

    /** NOTE .TODO
     * 자판기의 음료 가격, 이름 수정
     */
    @RequestMapping(key = "PATCH_VENDING_MACHINE_{drinkId}")
    public SocketResponse modifyVendingMachine(
        @KeyParameter Long drinkId,
        @RequestBody AdminRequest adminRequest){
        return SocketResponse.success(adminService.editVendingMachine(drinkId,adminRequest));
    }

    /** NOTE
     * 자판기 거스름돈 현황 파악
     */
    @RequestMapping(key = "GET_{vendingMachineId}")
    public SocketResponse getVendingMachineChangeStatus(@KeyParameter Long vendingMachineId){
        return SocketResponse.success(vendingMachineService.showCurrentStatus(vendingMachineId));
    }

    /** NOTE
     * 자판기 음료 재고 충전
     */
    @RequestMapping(key = "ADD_DRINK_{vendingMachineId}")
    public SocketResponse addDrink(
        @KeyParameter Long vendingMachineId,
        @RequestBody AdminStockRequest addStock
    ){
        return SocketResponse.success(stockService.putStock(vendingMachineId,addStock));
    }

    /** NOTE
     * 관리자 수금
     */
    @RequestMapping(key = "COLLECT_MONEY_{vendingMachineId}")
    public SocketResponse collectMoney(@KeyParameter Long vendingMachineId){
        return SocketResponse.success(adminService.collectBills(vendingMachineId));
    }

    /**
     * NOTE
     * 자판기 일별/월별 산출
     */
    @RequestMapping(key = "GET_STATISTICS_{vendingMachineId}")
    public SocketResponse getVendingMachineStatistics(@KeyParameter Long vendingMachineId) {
        return SocketResponse.success(orderService.statisticsSales(vendingMachineId,0L));
    }

    /**
     * NOTE
     * 각 자판기 각 음료별 일별/월별 매출
     */
    @RequestMapping(key = "GET_DRINK_STATISTICS_{vendingMachineId}")
    public SocketResponse getDrinkStatistics(
        @KeyParameter Long vendingMachineId,
        @RequestBody StatisticsRequest request
    ) {
        return SocketResponse.success(orderService.statisticsSales(
                vendingMachineId, request.drinkId()
            )
        );
    }

    @RequestMapping(key = "LOGIN")
    public SocketResponse login(@RequestBody AdminAccount adminAccount){
        return SocketResponse.success(adminService.loginAdmin(adminAccount));
    }
}
