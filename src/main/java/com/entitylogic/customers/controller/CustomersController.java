package com.entitylogic.customers.controller;

import com.entitylogic.customers.controller.response.GetCustomerProductsResponse;
import com.entitylogic.customers.controller.response.GetCustomerResponse;
import com.entitylogic.customers.dto.AccountDto;
import com.entitylogic.customers.dto.CardDto;
import com.entitylogic.customers.dto.CustomerDto;
import com.entitylogic.customers.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/customers", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
class CustomersController {

    private final ProductService productService;

    @GetMapping
    public GetCustomerResponse getCustomerResponse(@RequestParam long customerId) {
        return new GetCustomerResponse(new CustomerDto(1234l, "Jan", "Kowalski"));
    }

    @GetMapping("/{customerId}/products")
    public GetCustomerProductsResponse getCustomerProducts(@PathVariable Long customerId) {
        CustomerDto customerDto = new CustomerDto(1234l, "Jan", "Kowalski");

        log.info("Getting accounts for customer {}", customerId);
        List<AccountDto> customerAccounts = productService.findCustomerAccounts(customerId);

        log.info("Getting cards for customer {}", customerId);
        List<CardDto> customerCards = productService.findCustomerCards(customerId);

        return GetCustomerProductsResponse.builder()
                .customerId(customerDto.getId())
                .fullName(customerDto.getFirstName() + " " + customerDto.getLastName())
                .accounts(customerAccounts)
                .cards(customerCards)
                .build();
    }

}
