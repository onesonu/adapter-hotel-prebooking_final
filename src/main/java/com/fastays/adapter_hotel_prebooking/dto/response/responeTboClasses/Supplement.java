package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplement {
    @JsonProperty("Index")
    private int index;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Price")
    private double price;

    @JsonProperty("Currency")
    private String currency;
}

