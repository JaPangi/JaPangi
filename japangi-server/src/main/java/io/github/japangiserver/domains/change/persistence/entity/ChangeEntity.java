package io.github.japangiserver.domains.change.persistence.entity;

import io.github.japangiserver.domains.money.persistence.entity.MoneyEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** NOTE
 * 거스름돈 entity
 */
@Entity
@Table(name = "changes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changes_id")
    private Long changeId;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "money_id")
    private MoneyEntity moneyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vending_machine_id")
    private VendingMachineEntity vendingMachineEntity;

    @Builder
    public ChangeEntity(Integer amount, MoneyEntity moneyEntity, VendingMachineEntity vendingMachineEntity) {
        this.amount = amount;
        this.moneyEntity = moneyEntity;
        this.vendingMachineEntity = vendingMachineEntity;
    }

    public void remainChange(Integer amount){
        this.amount-=amount;
    }

    public void increaseAmount(Integer amount){
        this.amount+=amount;
    }
    public boolean validChanges(Integer amount){
        System.out.println("amount = " + amount);
        System.out.println("this" + this.amount);
        return this.amount >= amount;
    }

    public int collectMoneyByAdmin(){
        int amount = this.amount;
        this.amount=5;
        return amount-this.amount;
    }
}
