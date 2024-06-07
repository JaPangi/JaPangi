package io.github.japangiserver.domains.stock.mapper;

import io.github.japangiserver.domains.admin.dto.request.AdminStockRequest;
import io.github.japangiserver.domains.stock.domain.AddStock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public AddStock toAddStock(AdminStockRequest adminStockRequest){
        return new AddStock(adminStockRequest.drinkId(), adminStockRequest.amount());
    }

}
