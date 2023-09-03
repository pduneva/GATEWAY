package com.pduneva.gateway.controller;

import com.pduneva.gateway.exception.CurrencyRetrieveException;
import com.pduneva.gateway.exception.DuplicateRequestIdException;
import com.pduneva.gateway.model.CurrencyHistory;
import com.pduneva.gateway.model.api.CurrencyHistoryList;
import com.pduneva.gateway.model.api.CurrentRateRequest;
import com.pduneva.gateway.model.api.HistoryRateRequest;
import com.pduneva.gateway.service.ApiRequestHistoryService;
import com.pduneva.gateway.service.CurrencyHistoryService;
import com.pduneva.gateway.service.FixerRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AbstractApiController {

    private final FixerRestService fixerRestService;

    private final ApiRequestHistoryService apiRequestHistoryService;

    private final CurrencyHistoryService currencyHistoryService;

    public AbstractApiController(
            FixerRestService fixerRestService,
            ApiRequestHistoryService apiRequestHistoryService,
            CurrencyHistoryService currencyHistoryService
    ) {
        this.fixerRestService = fixerRestService;
        this.apiRequestHistoryService = apiRequestHistoryService;
        this.currencyHistoryService = currencyHistoryService;
    }

    public ResponseEntity getCurrentCurrencyRate(@RequestBody CurrentRateRequest currentRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException {
        apiRequestHistoryService.getApiRequestHistory(currentRateRequest, this.getClass().getSimpleName(), currentRateRequest.getRequestId());

        CurrencyHistory currencyHistory = currencyHistoryService.getCurrencyHistoryLast(currentRateRequest);
        return ResponseEntity.ok(currencyHistory);
    }

    public ResponseEntity getCurrentHistoryRate(@RequestBody HistoryRateRequest historyRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException {
        apiRequestHistoryService.getApiRequestHistory(historyRateRequest, this.getClass().getSimpleName(), historyRateRequest.getRequestId());

        List<CurrencyHistory> currencyHistoryList =
                currencyHistoryService.getCurrencyHistoryByPeriod(historyRateRequest);

        return ResponseEntity.ok(new CurrencyHistoryList(currencyHistoryList));
    }
}
