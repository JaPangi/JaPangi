package io.github.japangiserver.domains.order.persistence.entity;

import io.github.japangiserver.domains.drink.persistence.entity.DrinkEntity;
import io.github.japangiserver.domains.vendingmachine.persistence.entity.VendingMachineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/** NOTE
 * 주문 entity
 */
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private DrinkEntity drinkEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vending_machine_id")
    private VendingMachineEntity vendingMachineEntity;

    @Column(name = "ordered_at")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime orderedAt;
}
