package com.pduneva.gateway.model.api;

import com.pduneva.gateway.model.CurrencyRate;
import lombok.*;

import java.util.Map;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CurrencyInfoResponse {

    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<CurrencyRate, Double> rates;
}
