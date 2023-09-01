package com.pduneva.gateway.controller;

import com.pduneva.gateway.exception.CurrencyRetrieveException;
import com.pduneva.gateway.exception.DuplicateRequestIdException;
import com.pduneva.gateway.model.CurrencyHistory;
import com.pduneva.gateway.model.api.CurrentRateRequest;
import com.pduneva.gateway.model.api.HistoryRateRequest;
import com.pduneva.gateway.service.ApiRequestHistoryService;
import com.pduneva.gateway.service.CurrencyHistoryService;
import com.pduneva.gateway.service.FixerRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json_api")
public class JsonApiController {

    private final FixerRestService fixerRestService;

    private final ApiRequestHistoryService apiRequestHistoryService;

    private final CurrencyHistoryService currencyHistoryService;

    public JsonApiController(
            FixerRestService fixerRestService,
            ApiRequestHistoryService apiRequestHistoryService,
            CurrencyHistoryService currencyHistoryService
    ) {
        this.fixerRestService = fixerRestService;
        this.apiRequestHistoryService = apiRequestHistoryService;
        this.currencyHistoryService = currencyHistoryService;
    }

    @PostMapping(value = "/current", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCurrentCurrencyRate(@RequestBody CurrentRateRequest currentRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException {
        apiRequestHistoryService.getApiRequestHistory(currentRateRequest, this.getClass().getSimpleName(), currentRateRequest.getRequestId());

        CurrencyHistory currencyHistory = currencyHistoryService.getCurrencyHistoryLast(currentRateRequest);
        return ResponseEntity.ok(currencyHistory);
    }

    @PostMapping(value = "/history", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCurrentHistoryRate(@RequestBody HistoryRateRequest historyRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException {
        apiRequestHistoryService.getApiRequestHistory(historyRateRequest, this.getClass().getSimpleName(), historyRateRequest.getRequestId());

        List<CurrencyHistory> currencyHistoryList =
                currencyHistoryService.getCurrencyHistoryByPeriod(historyRateRequest);
        System.out.println(currencyHistoryList);
        return ResponseEntity.ok(currencyHistoryList);
    }


}
