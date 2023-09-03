package com.pduneva.gateway.model.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.pduneva.gateway.model.CurrencyRate;
import lombok.*;

import java.util.UUID;

@JacksonXmlRootElement(localName = "command")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CurrentRateRequestXml {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private UUID id;

    private Get get;

    @Getter @Setter @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class Get {

        @JacksonXmlProperty(isAttribute = true)

        private Long consumer;

        private CurrencyRate currency;

        private Long timestamp;
    }
}
