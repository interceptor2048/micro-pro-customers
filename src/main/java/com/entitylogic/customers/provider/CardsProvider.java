package com.entitylogic.customers.provider;

import com.entitylogic.customers.connector.CardsConnector;
import com.entitylogic.customers.dto.AccountDto;
import com.entitylogic.customers.dto.CardDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardsProvider {

    private final CardsConnector cardsConnector;

    @HystrixCommand(defaultFallback = "getCustomerCardsFallback")
    public List<CardDto> getCustomerCards(Long customerId) {
        return cardsConnector.getCards(customerId)
                .getCards()
                .stream()
                .map(card -> new CardDto(
                        card.getId(),
                        card.getNumber(),
                        card.getValidTo(),
                        card.getAvailableFunds()))
                .collect(Collectors.toList());
    }

    public List<CardDto> getCustomerCardsFallback() {
        log.warn("Cards service is not available");
        return Collections.emptyList();
    }

}
