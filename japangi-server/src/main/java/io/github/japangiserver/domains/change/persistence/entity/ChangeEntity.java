package io.github.japangiserver.domains.change.persistence.entity;

import io.github.japangiserver.domains.money.persistence.entity.MoneyEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    public void validChanges(Integer amount){
        if(this.amount<amount)
            throw new IllegalStateException("잔돈이 부족합니다");
    }

    public void collectMoneyByAdmin(){
        this.amount=5;
    }
}
