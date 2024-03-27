package io.github.japangiserver.domain.stock;

import io.github.japangiserver.domain.drink.Drink;
import io.github.japangiserver.domain.vendingmachine.VendingMachine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private Drink drink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendingmachine_id")
    private VendingMachine vendingMachine;

    @Column(name = "amount")
    private Integer amount;
}
