package com.pduneva.gateway.repository;

import com.pduneva.gateway.model.CurrencyHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyHistoryRepository extends CrudRepository<CurrencyHistory, Long> {
}
