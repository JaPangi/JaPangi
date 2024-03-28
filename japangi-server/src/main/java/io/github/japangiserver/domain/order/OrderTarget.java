package io.github.japangiserver.domain.order;

public record OrderTarget(
        long drinkId,
        long vendingMachineId
) {
}
