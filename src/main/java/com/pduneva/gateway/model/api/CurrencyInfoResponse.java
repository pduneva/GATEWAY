package com.pduneva.gateway.api.model;

import lombok.*;

import java.util.Map;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CurrencyInfoResponse {

    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
