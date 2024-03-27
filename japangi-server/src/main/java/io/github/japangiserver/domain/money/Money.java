package io.github.japangiserver.domain.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "money")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_id")
    private Long moneyId;

    @Column(name = "value")
    private Integer value;

    @Enumerated(EnumType.STRING)
    private MoneyType moneyType;
}
