package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CancelPolicy {
    @JsonProperty("Index")
    private String index;

    @JsonProperty("FromDate")
    private String fromDate;

    @JsonProperty("ChargeType")
    private String chargeType;

    @JsonProperty("CancellationCharge")
    private double cancellationCharge;
}

