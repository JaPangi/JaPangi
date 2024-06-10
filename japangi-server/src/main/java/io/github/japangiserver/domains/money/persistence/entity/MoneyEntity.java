package io.github.japangiserver.domains.money.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** NOTE
 * 화폐 entity
 */
@Entity
@Table(name = "money")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_id")
    private Long moneyId;

    @Column(name = "value")
    private Integer value;

    @Enumerated(EnumType.STRING)
    private MoneyType moneyType;

}
