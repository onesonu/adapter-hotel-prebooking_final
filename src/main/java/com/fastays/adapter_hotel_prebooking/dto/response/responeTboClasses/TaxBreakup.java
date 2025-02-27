package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaxBreakup {
    @JsonProperty("TaxType")
    private String taxType;

    @JsonProperty("TaxableAmount")
    private double taxableAmount;

    @JsonProperty("TaxPercentage")
    private double taxPercentage;

    @JsonProperty("TaxAmount")
    private double taxAmount;
}

