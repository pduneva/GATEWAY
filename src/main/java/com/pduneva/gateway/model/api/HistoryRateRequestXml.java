package com.pduneva.gateway.model.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.pduneva.gateway.model.CurrencyRate;
import lombok.*;

import java.util.UUID;

@JacksonXmlRootElement(localName = "command")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class HistoryRateRequestXml {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private UUID id;

    private History history;

    @Getter @Setter @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class History {

        @JacksonXmlProperty(isAttribute = true, localName = "consumer")
        private Long consumer;

        @JacksonXmlProperty(isAttribute = true, localName = "currency")
        private CurrencyRate currency;

        @JacksonXmlProperty(isAttribute = true, localName = "period")
        private int period;

        @JacksonXmlProperty(localName = "timestamp")
        private Long timestamp;

    }
}
