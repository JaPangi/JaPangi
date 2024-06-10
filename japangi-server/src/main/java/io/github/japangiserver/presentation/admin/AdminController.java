package io.github.japangiserver.presentation.admin;

import io.github.japangiserver.domains.admin.service.AdminService;
import io.github.japangiserver.domains.drink.DrinkInfo;
import io.github.japangiserver.domains.order.service.OrderService;
import io.github.japangiserver.domains.stock.service.StockService;
import io.github.japangiserver.domains.vendingmachine.service.VendingMachineService;
import io.github.japangiserver.presentation.admin.dto.request.AdminAccountRequest;
import io.github.japangiserver.presentation.admin.dto.request.AdminEditRequest;
import io.github.japangiserver.presentation.admin.dto.request.AdminRequest;
import io.github.japangiserver.presentation.admin.dto.request.AdminStockRequest;
import io.github.japangiserver.presentation.admin.dto.request.StatisticsRequest;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.KeyParameter;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestBody;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.RequestMapping;
import io.wwan13.dispatchersorvlet.sorvlet.annotation.SocketController;
import io.wwan13.dispatchersorvlet.sorvlet.dto.response.SocketResponse;
import lombok.RequiredArgsConstructor;

/** NOTE
 *  관리자 기능 controller
 */
@SocketController
@RequestMapping(key = "ADMIN")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final StockService stockService;
    private final VendingMachineService vendingMachineService;
    private final OrderService orderService;

    /** NOTE
     * 관리자 회원가입 API
     * @param adminAccountRequest 아이디, 패스워드 입력
     */
    @RequestMapping(key = "CREATE")
    public SocketResponse signUpAdmin(@RequestBody AdminAccountRequest adminAccountRequest){
        return SocketResponse.success(adminService.createAdmin(adminAccountRequest.toAdmin()));
    }

    /** NOTE
     *  관리자 비밀번호 변경 API
     * @param adminId 변경하려는 관리자 Id(PK)
     * @param adminEditRequest 변경하려는 비밀번호
     */
    @RequestMapping(key = "PATCH_{adminId}")
    public SocketResponse modifyAdmin(
        @KeyParameter Long adminId,
        @RequestBody AdminEditRequest adminEditRequest
    ){
        return SocketResponse.success(
            adminService.editAdmin(adminId, adminEditRequest.toAdminPassword())
        );
    }

    /** NOTE
     * 자판기의 음료 가격, 이름 수정 API
     * @param drinkId 변경하려는 음료Id(PK)
     * @param adminRequest 변경할 음료 이름 및 음료 가격
     */
    @RequestMapping(key = "PATCH_VENDING_MACHINE_{drinkId}")
    public SocketResponse modifyVendingMachine(
        @KeyParameter Long drinkId,
        @RequestBody
        AdminRequest adminRequest){
        return SocketResponse.success(adminService.editVendingMachine(adminRequest.toDrink(drinkId)));
    }

    /** NOTE
     * 자판기 거스름돈 현황 파악 API
     * @param vendingMachineId 현황 파악하려는 자판기 Id(PK)
     */
    @RequestMapping(key = "GET_{vendingMachineId}")
    public SocketResponse getVendingMachineChangeStatus(@KeyParameter Long vendingMachineId){
        return SocketResponse.success(vendingMachineService.showCurrentStatus(vendingMachineId));
    }

    /** NOTE
     * 자판기 음료 재고 충전 API
     * @param vendingMachineId 재고 충전하려는 자판기 Id(PK)
     * @param addStock 재고를 보충해줄 음료Id(PK)와 수량
     */
    @RequestMapping(key = "ADD_DRINK_{vendingMachineId}")
    public SocketResponse addDrink(
        @KeyParameter Long vendingMachineId,
        @RequestBody AdminStockRequest addStock
    ){
        return SocketResponse.success(stockService.putStock(addStock.toAddStock(vendingMachineId)));
    }

    /** NOTE
     * 관리자 수금 API
     * @param vendingMachineId 관리자가 수금하려는 자판기 Id(PK)
     */
    @RequestMapping(key = "COLLECT_MONEY_{vendingMachineId}")
    public SocketResponse collectMoney(@KeyParameter Long vendingMachineId){
        return SocketResponse.success(adminService.collectBills(vendingMachineId));
    }

    /** NOTE
     * 자판기 일별/월별 산출 API
     * @param vendingMachineId 매출을 산출할 자판기 Id(PK)
     */
    @RequestMapping(key = "GET_STATISTICS_{vendingMachineId}")
    public SocketResponse getVendingMachineStatistics(@KeyParameter Long vendingMachineId) {
        return SocketResponse.success(orderService.statisticsSales(
            vendingMachineId,new DrinkInfo(0L))
        );
    }

    /** NOTE
     * 각 자판기 각 음료별 일별/월별 매출 API
     * @param vendingMachineId 매출을 산출할 자판기 Id(PK)
     * @param request 산출할 음료의 Id(PK)
     */
    @RequestMapping(key = "GET_DRINK_STATISTICS_{vendingMachineId}")
    public SocketResponse getDrinkStatistics(
        @KeyParameter Long vendingMachineId,
        @RequestBody StatisticsRequest request
    ) {
        return SocketResponse.success(orderService.statisticsSales(
                vendingMachineId, request.toDrinkInfo()
            )
        );
    }

    /** NOTE
     * 관리자 로그인 API
     * @param adminAccountRequest 관리자 아이디와 비밀번호
     */
    @RequestMapping(key = "LOGIN")
    public SocketResponse login(@RequestBody AdminAccountRequest adminAccountRequest){
        return SocketResponse.success(adminService.loginAdmin(adminAccountRequest.toAdmin()));
    }

    /** NOTE
     * 자판기 재고 현황 파악
     * @param vendingMachineId 재고를 파악하려는 자판기 Id(PK)
     */
    @RequestMapping(key = "GET_{vendingMachineId}")
    public SocketResponse getVendingMachineStockStatus(@KeyParameter Long vendingMachineId) {
        return SocketResponse.success(
            vendingMachineService.showCurrentStockStatus(vendingMachineId));
    }
}
