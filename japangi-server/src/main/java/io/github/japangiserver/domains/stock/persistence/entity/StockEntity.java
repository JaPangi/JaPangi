package io.github.japangiserver.domains.stock.persistence.entity;

import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** NOTE
 * 재고 entity
 */
@Entity
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private DrinkEntity drinkEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendingmachine_id")
    private VendingMachineEntity vendingMachineEntity;

    @Column(name = "amount")
    private Integer amount;

    @Builder
    public StockEntity(DrinkEntity drinkEntity, VendingMachineEntity vendingMachineEntity, Integer amount) {
        this.drinkEntity = drinkEntity;
        this.vendingMachineEntity = vendingMachineEntity;
        this.amount = amount;
    }

    public void removeAmount(){
        this.amount-=1;
    }

    public void increaseAmount(int amount){
        this.amount+=amount;
    }
}
