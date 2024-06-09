package io.github.japangiserver.domains.money.serviceimpl;

import io.github.japangiserver.domains.money.persistence.entity.MoneyEntity;
import io.github.japangiserver.domains.money.persistence.entity.MoneyType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoneySaver {
    public static final Map<Integer , MoneyType> moneyTypeMap = new HashMap<>();
    private final MoneyReader moneyReader;

    @PostConstruct
    public List<MoneyEntity> initMoneyMap(){
        List<MoneyEntity> moneyEntityList = moneyReader.getMoneyList();
        for(MoneyEntity moneyEntity : moneyEntityList){
            moneyTypeMap.put(moneyEntity.getValue(), moneyEntity.getMoneyType());
        }
        return moneyEntityList;
    }
}