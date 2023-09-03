package com.pduneva.gateway.model.api;

import com.pduneva.gateway.model.CurrencyRate;
import lombok.*;

import java.util.UUID;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CurrentRateRequest {

    private UUID requestId;

    private Long timestamp;

    private Long client;

    private CurrencyRate currency;
}
