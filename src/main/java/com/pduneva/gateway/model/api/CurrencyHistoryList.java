package com.pduneva.gateway.model.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.pduneva.gateway.model.CurrencyHistory;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CurrencyHistoryList {

    @JacksonXmlElementWrapper(localName = "currencyHistory")
    @JacksonXmlProperty(localName = "currencyHistory")
    private List<CurrencyHistory> currencyHistoryList;
}
