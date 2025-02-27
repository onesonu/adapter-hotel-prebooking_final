package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status {
    @JsonProperty("Code")
    private int code;

    @JsonProperty("Description")
    private String description;
}

