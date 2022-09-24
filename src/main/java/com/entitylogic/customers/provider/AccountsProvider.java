package com.entitylogic.customers.provider;

import com.entitylogic.customers.connector.AccountsConnector;
import com.entitylogic.customers.connector.response.GetAccountsResponse;
import com.entitylogic.customers.dto.AccountDto;
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
public class AccountsProvider {
    private final AccountsConnector accountsConnector;

    @HystrixCommand(defaultFallback = "getCustomerAccountsFallback")
    public List<AccountDto> getCustomerAccounts(Long customerId) {
        return accountsConnector.getAccounts(customerId)
                .getAccounts()
                .stream()
                .map(account -> new AccountDto(
                        account.getId(),
                        account.getNrb(),
                        account.getCurrency(),
                        account.getAvailableFunds()))
                .collect(Collectors.toList());
    }

    public List<AccountDto> getCustomerAccountsFallback() {
        log.warn("Accounts service is not available");
        return Collections.emptyList();
    }

}