package io.github.japangiserver.domains.vendingmachine.service;

import io.github.japangiserver.domains.change.serviceimpl.ChangeReader;
import io.github.japangiserver.domains.change.serviceimpl.ChangeSaver;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.service.serviceimpl.StockSaver;
import io.github.japangiserver.domains.vendingmachine.service.serviceimpl.VendingMachineReader;
import io.github.japangiserver.domains.vendingmachine.service.serviceimpl.VendingMachineSaver;
import io.github.japangiserver.presentation.change.dto.response.ChangeStatusResponse;
import io.github.japangiserver.presentation.stock.dto.response.StockStatusResponse;
import io.github.japangiserver.presentation.vendingmachine.dto.response.VendingMachineResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 자판기 핵심 비즈니스 로직 service Layer
 */
@Service
@RequiredArgsConstructor
public class VendingMachineService {

    private final VendingMachineReader vendingMachineReader;
    private final VendingMachineSaver vendingMachineSaver;
    private final StockSaver stockSaver;
    private final ChangeSaver changeSaver;
    private final ChangeReader changeReader;
    private final StockReader stockReader;

    /** NOTE
     * 자판기 초기 셋팅 서비스
     */
    @Transactional
    public long init() {
        Long vendingMachineId = vendingMachineSaver.saveVendingMachine();

        stockSaver.initStock(vendingMachineId); //자판기 재고 초기화
        changeSaver.initChange(vendingMachineId); //자판기 거스름돈 초기화

        return vendingMachineId;
    }

    /** NOTE
     * 현재 거스름돈 현황 조회 서비스
     * @param vendingMachineId 자판기 Id(PK)
     */
    public List<ChangeStatusResponse> showCurrentStatus(Long vendingMachineId) {
        return changeReader.getCurrentStatus(vendingMachineId);
    }

    /** NOTE
     * 자판기 목록 조회 서비스
     */
    public List<VendingMachineResponse> showVendingMachines() {
        return vendingMachineReader.vendingMachineList();
    }

    /** NOTE
     * 자판기 재고 조회 서비스
     * @param vendingMachineId 자판기 Id(PK)
     */
    public List<StockStatusResponse> showCurrentStockStatus(Long vendingMachineId) {
        return stockReader.getCurrentStockStatus(vendingMachineId);
    }
}
