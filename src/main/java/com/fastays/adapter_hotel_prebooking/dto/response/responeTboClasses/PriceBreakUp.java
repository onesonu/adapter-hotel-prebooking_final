package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceBreakUp {
    @JsonProperty("RoomRate")
    private double roomRate;

    @JsonProperty("RoomTax")
    private double roomTax;

    @JsonProperty("AgentCommission")
    private double agentCommission;

    @JsonProperty("TaxBreakup")
    private List<TaxBreakup> taxBreakup;
}

