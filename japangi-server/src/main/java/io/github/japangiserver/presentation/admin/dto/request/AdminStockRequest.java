package io.github.japangiserver.presentation.admin.dto.request;

import io.github.japangiserver.domains.stock.AddStock;

public record AdminStockRequest(long drinkId, int amount) {
    public AddStock toAddStock(long vendingMachineId){
        return new AddStock(drinkId,amount,vendingMachineId);
    }
}
