package com.entitylogic.customers.service;

import com.entitylogic.customers.dto.AccountDto;
import com.entitylogic.customers.dto.CardDto;
import com.entitylogic.customers.provider.AccountsProvider;
import com.entitylogic.customers.provider.CardsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final AccountsProvider accountProvider;
    private final CardsProvider cardsProvider;

    public List<AccountDto> findCustomerAccounts(Long customerId) {
        return accountProvider.getCustomerAccounts(customerId);
    }

    public List<CardDto> findCustomerCards(Long customerId) {
        return cardsProvider.getCustomerCards(customerId);
    }
}