package com.pduneva.gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.util.UUID;

@Entity @Getter @Setter
@ToString @AllArgsConstructor
@NoArgsConstructor
public class ApiRequestHistory {

    @Id
    private UUID requestId;

    private String serviceName;

    private Long client;

    private Long timestamp;

    private CurrencyRate currencyRate;

    private Long created;

    @PrePersist
    protected void onCreate() {
        created = System.currentTimeMillis();
    }
}
