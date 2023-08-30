package com.pduneva.gateway.service;

import com.pduneva.gateway.model.api.CurrencyInfoResponse;
import com.pduneva.gateway.exception.CurrencyRetrieveException;
import com.pduneva.gateway.model.CurrencyRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FixerRestService {

    private final String API_URL;

    private final String API_KEY;

    private final RestTemplate restTemplate;

    public FixerRestService(
            @Value("${fixer.base.url}") String API_URL,
            @Value("${fixer.api.key}") String API_KEY
    ) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
        restTemplate = new RestTemplate();
    }

    public CurrencyInfoResponse getCurrencyInfo(CurrencyRate rate) throws CurrencyRetrieveException {
        ResponseEntity<CurrencyInfoResponse> response = restTemplate
                .getForEntity(getRequestPath() + "&symbols=" + rate.name(), CurrencyInfoResponse.class);
        if(response.getBody() == null || !response.getBody().isSuccess()) {
            throw new CurrencyRetrieveException("Failed to retrieve currency!");
        }
        return response.getBody();
    }

    public CurrencyInfoResponse getAllCurrencyInfo() throws CurrencyRetrieveException {
        ResponseEntity<CurrencyInfoResponse> response = restTemplate
                .getForEntity(getRequestPath(), CurrencyInfoResponse.class);
        if(response.getBody() == null || !response.getBody().isSuccess()) {
            throw new CurrencyRetrieveException("Failed to retrieve currencies!");
        }
        return response.getBody();
    }


    private String getRequestPath() {
        return API_URL + "latest?access_key=" + API_KEY;
    }


}
