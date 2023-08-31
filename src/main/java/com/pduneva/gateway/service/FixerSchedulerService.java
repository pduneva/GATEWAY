package com.pduneva.gateway.service;

import com.pduneva.gateway.exception.CurrencyRetrieveException;
import com.pduneva.gateway.model.api.CurrencyInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class FixerSchedulerService {

    private final FixerRestService fixerRestService;

    private final CurrencyHistoryService currencyHistoryService;

    private static final Logger logger = LoggerFactory.getLogger(FixerSchedulerService.class);


    public FixerSchedulerService(FixerRestService fixerRestService, CurrencyHistoryService currencyHistoryService) {
        this.fixerRestService = fixerRestService;
        this.currencyHistoryService = currencyHistoryService;
    }

    @Scheduled(fixedRateString = "${fixer.receive.rate}")
    public void getCurrenciesJob() {
        try {
            logger.info("Starting job for saving currencies in database");
            CurrencyInfoResponse currencies = fixerRestService.getAllCurrencyInfo();
            currencyHistoryService.saveCurrenciesOnBulk(currencies);
        } catch (CurrencyRetrieveException e) {
            logger.error("An error occurred in the job:", e.getMessage());
        } catch (ParseException e) {
            logger.error("An error occurred while parsing date:", e.getMessage());
        }
    }


}
