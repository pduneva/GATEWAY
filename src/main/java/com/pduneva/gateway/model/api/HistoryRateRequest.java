package com.pduneva.gateway.model.api;


import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class HistoryRateRequest extends CurrentRateRequest {

    private int period;

    public Long getTimestampStart() {
        return this.getTimestamp() - period * 60 * 60 * 1000;
    }
}
