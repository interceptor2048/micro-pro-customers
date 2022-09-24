package com.entitylogic.customers.connector;

import com.entitylogic.customers.connector.response.GetCardsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards")
public interface CardsConnector {

    @GetMapping("/v1/cards")
    GetCardsResponse getCards(@RequestParam("customerId") Long customerId);
}
