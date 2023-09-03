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
public class JsonApiController extends AbstractApiController {

    public JsonApiController(
            FixerRestService fixerRestService,
            ApiRequestHistoryService apiRequestHistoryService,
            CurrencyHistoryService currencyHistoryService
    ) {
        super(fixerRestService, apiRequestHistoryService, currencyHistoryService);
    }

    @PostMapping(value = "/current", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCurrentCurrencyRate(@RequestBody CurrentRateRequest currentRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException {

        return super.getCurrentCurrencyRate(currentRateRequest);
    }

    @PostMapping(value = "/history", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCurrentHistoryRate(@RequestBody HistoryRateRequest historyRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException {

        return super.getCurrentHistoryRate(historyRateRequest);
    }


}
