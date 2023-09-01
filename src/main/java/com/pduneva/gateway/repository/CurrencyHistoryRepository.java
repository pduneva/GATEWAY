package com.pduneva.gateway.repository;

import com.pduneva.gateway.model.CurrencyHistory;
import com.pduneva.gateway.model.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyHistoryRepository extends CrudRepository<CurrencyHistory, Long> {
    List<CurrencyHistory> findByCurrencyAndTimestampBetweenOrderByTimestampDesc(
            CurrencyRate currency, Long startTimestamp, Long endTimestamp);

    CurrencyHistory findFirstByCurrencyOrderByTimestampDesc(CurrencyRate currency);
}
