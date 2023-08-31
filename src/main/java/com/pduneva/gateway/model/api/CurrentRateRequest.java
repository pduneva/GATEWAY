package com.pduneva.gateway.model.api;

import com.pduneva.gateway.model.CurrencyRate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity
public class CurrentRateRequest {

    @Id
    private UUID requestId;

    private Long timestamp;

    private Long client;

    private CurrencyRate currency;

}
