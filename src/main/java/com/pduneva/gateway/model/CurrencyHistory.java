package com.pduneva.gateway.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CurrencyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long timestamp;

    private String base;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Enumerated(EnumType.STRING)
    private CurrencyRate currency;

    private Double rate;

    private Long created;

    @PrePersist
    protected void onCreate() {
        created = System.currentTimeMillis();
    }

}
