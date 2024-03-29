package io.github.japangiserver.domain.drink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "drink")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Drink {
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
    private String drinkImgUrl;
}
