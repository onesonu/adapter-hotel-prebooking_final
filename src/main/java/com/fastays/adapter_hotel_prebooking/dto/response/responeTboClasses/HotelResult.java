package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelResult {
    @JsonProperty("HotelCode")
    private String hotelCode;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("Rooms")
    private List<Room> rooms;

    @JsonProperty("RateConditions")
    private List<String> rateConditions;
}

