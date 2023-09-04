package com.pduneva.gateway.service;


import com.pduneva.gateway.exception.DuplicateRequestIdException;
import com.pduneva.gateway.model.ApiRequestHistory;
import com.pduneva.gateway.model.api.CurrentRateRequest;
import com.pduneva.gateway.repository.ApiRequestHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ApiRequestHistoryService {

    private final ModelMapper modelMapper;

    private final ApiRequestHistoryRepository apiRequestHistoryRepository;

    private final RabbitMqService rabbitMqService;

    public ApiRequestHistoryService(
            ApiRequestHistoryRepository apiRequestHistoryRepository,
            RabbitMqService rabbitMqService
    ) {
        this.modelMapper = new ModelMapper();
        this.apiRequestHistoryRepository = apiRequestHistoryRepository;
        this.rabbitMqService = rabbitMqService;
    }

    @CacheEvict(value = "request", key = "#requestId")
    public ApiRequestHistory saveApiRequestHistory(
            CurrentRateRequest currentRateRequest,
            String serviceName,
            UUID requestId

    ) {
        ApiRequestHistory apiRequestHistory = modelMapper.map(currentRateRequest, ApiRequestHistory.class);
        apiRequestHistory.setServiceName(serviceName);
        return apiRequestHistoryRepository.save(apiRequestHistory);
    }

    @Cacheable(value = "request", key = "#requestId")
    public ApiRequestHistory getApiRequestHistory(
            CurrentRateRequest currentRateRequest,
            String serviceName,
            UUID requestId
    ) throws DuplicateRequestIdException {

        ApiRequestHistory apiRequestHistory =
                apiRequestHistoryRepository.findById(currentRateRequest.getRequestId()).orElse(null);

        return apiRequestHistory;
    }

    public ApiRequestHistory checkApiRequestHistory(
            CurrentRateRequest currentRateRequest,
            String serviceName,
            UUID requestId
    ) throws DuplicateRequestIdException {
        rabbitMqService.sendMessage(currentRateRequest.toString());

        ApiRequestHistory apiRequestHistory = getApiRequestHistory(currentRateRequest, serviceName, requestId);
        if(apiRequestHistory != null) {
            throw new DuplicateRequestIdException("Request id: " + currentRateRequest.getRequestId());
        }
        apiRequestHistory = saveApiRequestHistory(currentRateRequest, serviceName, requestId);
        return apiRequestHistory;
    }

}
