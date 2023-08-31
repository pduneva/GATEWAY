package com.pduneva.gateway.service;


import com.pduneva.gateway.exception.DuplicateRequestIdException;
import com.pduneva.gateway.model.ApiRequestHistory;
import com.pduneva.gateway.model.api.CurrentRateRequest;
import com.pduneva.gateway.repository.ApiRequestHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class ApiRequestHistoryService {

    private final ModelMapper modelMapper;

    private final ApiRequestHistoryRepository apiRequestHistoryRepository;

    public ApiRequestHistoryService(ApiRequestHistoryRepository apiRequestHistoryRepository) {
        this.apiRequestHistoryRepository = apiRequestHistoryRepository;
        this.modelMapper = new ModelMapper();
    }

    public ApiRequestHistory saveApiRequestHistory(CurrentRateRequest currentRateRequest, String serviceName) {
        ApiRequestHistory apiRequestHistory = modelMapper.map(currentRateRequest, ApiRequestHistory.class);
        apiRequestHistory.setServiceName(serviceName);
        return apiRequestHistoryRepository.save(apiRequestHistory);
    }

    public ApiRequestHistory getApiRequestHistory(CurrentRateRequest currentRateRequest, String serviceName)
            throws DuplicateRequestIdException {
        ApiRequestHistory apiRequestHistory =
                apiRequestHistoryRepository.findById(currentRateRequest.getRequestId()).orElse(null);
        if(apiRequestHistory != null) {
            throw new DuplicateRequestIdException("Request id: " + currentRateRequest.getRequestId());
        }
        apiRequestHistory = saveApiRequestHistory(currentRateRequest, serviceName);
        return apiRequestHistory;
    }

}
