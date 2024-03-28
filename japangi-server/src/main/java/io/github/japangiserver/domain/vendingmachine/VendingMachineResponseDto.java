package io.github.japangiserver.domain.vendingmachine;

public record VendingMachineResponseDto(
        String drinkName,
        Integer drinkStock
) {
}
