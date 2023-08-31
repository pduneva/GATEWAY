package com.pduneva.gateway.repository;

import com.pduneva.gateway.model.ApiRequestHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApiRequestHistoryRepository extends CrudRepository<ApiRequestHistory, UUID> {
}
