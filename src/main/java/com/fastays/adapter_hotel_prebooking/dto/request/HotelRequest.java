package com.fastays.adapter_hotel_prebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
    @JsonProperty("BookingCode")
    private String bookingCode;
    @JsonProperty("PaymentMode")
    private String paymentMode;
}
