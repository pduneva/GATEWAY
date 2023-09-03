package com.pduneva.gateway.service;

import com.pduneva.gateway.model.api.CurrentRateRequest;
import com.pduneva.gateway.model.api.CurrentRateRequestXml;
import com.pduneva.gateway.model.api.HistoryRateRequest;
import com.pduneva.gateway.model.api.HistoryRateRequestXml;
import org.springframework.stereotype.Service;


@Service
public class XmlMapperService {


    public CurrentRateRequest mapFromXml(CurrentRateRequestXml xmlRequest) {
        CurrentRateRequest request = new CurrentRateRequest();
        request.setRequestId(xmlRequest.getId());

        CurrentRateRequestXml.Get xmlGet = xmlRequest.getGet();
        request.setClient(xmlGet.getConsumer());
        request.setCurrency(xmlGet.getCurrency());
        request.setTimestamp(xmlGet.getTimestamp());
        return request;
    }

    public HistoryRateRequest mapToCurrentRateRequest(HistoryRateRequestXml source) {
        HistoryRateRequest target = new HistoryRateRequest();
        target.setRequestId(source.getId());
        target.setTimestamp(source.getHistory().getTimestamp());
        target.setClient(source.getHistory().getConsumer());
        target.setCurrency(source.getHistory().getCurrency());
        target.setPeriod(source.getHistory().getPeriod());
        return target;
    }

}
