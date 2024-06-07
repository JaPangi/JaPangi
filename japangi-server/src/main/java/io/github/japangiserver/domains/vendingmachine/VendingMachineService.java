package io.github.japangiserver.domains.vendingmachine;

import io.github.japangiserver.domains.change.dto.response.ChangeStatus;
import io.github.japangiserver.domains.change.serviceimpl.ChangeReader;
import io.github.japangiserver.domains.change.serviceimpl.ChangeSaver;
import io.github.japangiserver.domains.stock.application.dto.response.StockStatus;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockReader;
import io.github.japangiserver.domains.stock.application.serviceimpl.StockSaver;
import io.github.japangiserver.domains.vendingmachine.dto.response.VendingMachineResponse;
import io.github.japangiserver.domains.vendingmachine.serviceimpl.VendingMachineReader;
import io.github.japangiserver.domains.vendingmachine.serviceimpl.VendingMachineSaver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendingMachineService {

    private final VendingMachineReader vendingMachineReader;
    private final VendingMachineSaver vendingMachineSaver;
    private final StockSaver stockSaver;
    private final ChangeSaver changeSaver;
    private final ChangeReader changeReader;
    private final StockReader stockReader;
    @Transactional
    public long init() {
        VendingMachineEntity vendingMachineEntity = new VendingMachineEntity();
        Long vendingMachineId = vendingMachineSaver.saveVendingMachine(vendingMachineEntity);

        stockSaver.initStock(vendingMachineEntity); //자판기 재고 초기화
        changeSaver.initChange(vendingMachineEntity); //자판기 거스름돈 초기화

        return vendingMachineId;
    }

    @Transactional(readOnly = true)
    public List<ChangeStatus> showCurrentStatus(Long vendingMachineId) {
        return changeReader.getCurrentStatus(vendingMachineId);
    }

    @Transactional(readOnly = true)
    public List<VendingMachineResponse> showVendingMachines() {
        return vendingMachineReader.vendingMachineList();
    }

    @Transactional(readOnly = true)
    public List<StockStatus> showCurrentStockStatus(Long vendingMachineId) {
        return stockReader.getCurrentStockStatus(vendingMachineId);
    }
}
