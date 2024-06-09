package io.github.japangiserver.domains.order.service.serviceimpl;

import org.springframework.stereotype.Component;

@Component
public class OrderCalculator {
    public int calculateChanges(int totalPrice, int drinkPrice){
        return totalPrice - drinkPrice;
    }
}
