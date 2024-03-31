package io.github.japangiserver.domain.vendingmachine;

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
public class VendingMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendingmachine_id")
    private Long vendingMachineId;
}
