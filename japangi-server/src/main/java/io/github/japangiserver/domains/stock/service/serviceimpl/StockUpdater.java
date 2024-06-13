package io.github.japangiserver.domains.stock.service.serviceimpl;

import io.github.japangiserver.domains.stock.AddStock;
import io.github.japangiserver.domains.stock.persistence.StockEntityReader;
import io.github.japangiserver.domains.stock.persistence.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** NOTE
 * 재고 업데이트 implement Layer
 */
@Component
@RequiredArgsConstructor
public class StockUpdater {
    private final StockEntityReader stockEntityReader;

    /** NOTE
     * 재고 감소 구현체
     * @param stock stock domain
     */
    @Transactional
    public void updateRemoveAmount(AddStock stock){
        StockEntity stockEntity = stockEntityReader.getStockEntity(stock);
        stockEntity.removeAmount();
    }

    /** NOTE
     * 재고 증가 구현체
     * @param addStock AddStock domain
     */
    @Transactional
    public void updateAddAmount(AddStock addStock,int amount) {
        StockEntity stockEntity = stockEntityReader.getStockEntity(addStock);
        stockEntity.increaseAmount(amount);
    }

}
