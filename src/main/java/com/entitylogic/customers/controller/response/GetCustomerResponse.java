package com.entitylogic.customers.controller.response;

import com.entitylogic.customers.dto.CustomerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetCustomerResponse extends CustomerDto {

    public GetCustomerResponse(CustomerDto customerDto) {
        super(customerDto.getId(), customerDto.getFirstName(), customerDto.getLastName());
    }
}