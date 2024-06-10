package io.github.japangiserver.domains.drink.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** NOTE
 * 음료 entity
 */
@Entity
@Table(name = "drink")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DrinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name = "drink_name")
    private String drinkName;

    @Column(name = "drink_price")
    private Integer drinkPrice;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "drink_img_url")
    private String drinkImageUrl;

    public void updateDrink(String drinkName, Integer drinkPrice){
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
    }
}
