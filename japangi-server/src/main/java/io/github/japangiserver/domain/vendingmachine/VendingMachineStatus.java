package io.github.japangiserver.domain.vendingmachine;

import io.github.japangiserver.domain.drink.DrinkStatus;
import java.util.List;

public record VendingMachineStatus(
    List<DrinkStatus> drinkStatuses
) {

}
