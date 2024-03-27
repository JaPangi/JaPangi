package io.github.japangiserver.domain.change;

import io.github.japangiserver.domain.money.Money;
import io.github.japangiserver.domain.vendingmachine.VendingMachine;
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
public class Change {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changes_id")
    private Long changeId;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "money_id")
    private Money money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendingmachine_id")
    private VendingMachine vendingMachine;

    @Builder
    public Change(Integer amount, Money money, VendingMachine vendingMachine) {
        this.amount = amount;
        this.money = money;
        this.vendingMachine = vendingMachine;
    }
}
