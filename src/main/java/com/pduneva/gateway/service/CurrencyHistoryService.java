package com.pduneva.gateway.service;

import com.pduneva.gateway.model.CurrencyHistory;
import com.pduneva.gateway.model.CurrencyRate;
import com.pduneva.gateway.model.api.CurrencyInfoResponse;
import com.pduneva.gateway.repository.CurrencyHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CurrencyHistoryService {

    private final CurrencyHistoryRepository currencyHistoryRepository;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    public CurrencyHistoryService(CurrencyHistoryRepository currencyHistoryRepository) {
        this.currencyHistoryRepository = currencyHistoryRepository;
    }

    @Transactional
    public void saveCurrenciesOnBulk(CurrencyInfoResponse currencyInfoResponse) throws ParseException {
        List<CurrencyHistory> currencyHistoryList = new ArrayList<>();
        for(CurrencyRate currencyRate: currencyInfoResponse.getRates().keySet()) {
            CurrencyHistory currencyHistory = createCurrencyRateHistory(currencyInfoResponse);
            currencyHistory.setRate(currencyInfoResponse.getRates().get(currencyRate));
            currencyHistory.setCurrency(currencyRate);
            currencyHistoryList.add(currencyHistory);
        }
        currencyHistoryRepository.saveAll(currencyHistoryList);
    }

    private CurrencyHistory createCurrencyRateHistory(CurrencyInfoResponse currencyInfoResponse) throws ParseException {
        CurrencyHistory currencyHistory = new CurrencyHistory();
        currencyHistory.setTimestamp(currencyInfoResponse.getTimestamp());
        currencyHistory.setBase(currencyInfoResponse.getBase());
        currencyHistory.setDate(formatter.parse(currencyInfoResponse.getDate()));
        return currencyHistory;
    }

}
