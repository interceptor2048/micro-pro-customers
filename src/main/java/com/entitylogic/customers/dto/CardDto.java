package com.entitylogic.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    private long id;
    private String number;
    private LocalDate validTo;
    private BigDecimal availableFunds;
}
