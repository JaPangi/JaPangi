package io.github.japangiserver.domains.vendingmachine.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "vendingmachine")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class VendingMachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vending_machine_id")
    private Long vendingMachineId;
}
