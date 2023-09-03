package com.pduneva.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@XmlRootElement
public class CurrencyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
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
