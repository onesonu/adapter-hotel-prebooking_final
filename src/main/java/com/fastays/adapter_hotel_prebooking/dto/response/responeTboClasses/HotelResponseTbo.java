package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelResponseTbo {
    @JsonProperty("Status")
    private Status status;

    @JsonProperty("HotelResult")
    private List<HotelResult> hotelResult;

    @JsonProperty("ValidationInfo")
    private ValidationInfo validationInfo;
}
