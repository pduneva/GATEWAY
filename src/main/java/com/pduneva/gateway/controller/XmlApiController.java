package com.pduneva.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pduneva.gateway.exception.CurrencyRetrieveException;
import com.pduneva.gateway.exception.DuplicateRequestIdException;
import com.pduneva.gateway.model.api.CurrentRateRequest;
import com.pduneva.gateway.model.api.CurrentRateRequestXml;
import com.pduneva.gateway.model.api.HistoryRateRequest;
import com.pduneva.gateway.model.api.HistoryRateRequestXml;
import com.pduneva.gateway.service.ApiRequestHistoryService;
import com.pduneva.gateway.service.CurrencyHistoryService;
import com.pduneva.gateway.service.FixerRestService;
import com.pduneva.gateway.service.XmlMapperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/xml_api/")
public class XmlApiController extends AbstractApiController {

    private final XmlMapperService xmlMapperService;


    public XmlApiController(
            FixerRestService fixerRestService,
            ApiRequestHistoryService apiRequestHistoryService,
            CurrencyHistoryService currencyHistoryService,
            XmlMapperService xmlMapperService
    ) {
        super(fixerRestService, apiRequestHistoryService, currencyHistoryService);
        this.xmlMapperService = xmlMapperService;
    }

    @PostMapping(value = "/command", consumes = "application/xml", produces = "application/xml")
    @ResponseBody
    public ResponseEntity getCurrentCurrencyRate(@RequestBody CurrentRateRequestXml currentRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException, JsonProcessingException {

        CurrentRateRequest simpleCurrentRateRequest = xmlMapperService.mapFromXml(currentRateRequest);

        return super.getCurrentCurrencyRate(simpleCurrentRateRequest);
    }

    @PostMapping(value = "/history", consumes = "application/xml", produces = "application/xml")
    @ResponseBody
    public ResponseEntity getCurrentHistoryRate(@RequestBody HistoryRateRequestXml currentRateRequest)
            throws CurrencyRetrieveException, DuplicateRequestIdException, JsonProcessingException {

        HistoryRateRequest simpleCurrentRateRequest = xmlMapperService.mapToCurrentRateRequest(currentRateRequest);

        return super.getCurrentHistoryRate(simpleCurrentRateRequest);
    }
}
