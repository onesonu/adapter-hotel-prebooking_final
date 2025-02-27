package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    @JsonProperty("Name")
    private List<String> name;

    @JsonProperty("BookingCode")
    private String bookingCode;

    @JsonProperty("Inclusion")
    private String inclusion;

    @JsonProperty("DayRates")
    private List<List<DayRate>> dayRates;

    @JsonProperty("TotalFare")
    private double totalFare;

    @JsonProperty("TotalTax")
    private double totalTax;

    @JsonProperty("RoomPromotion")
    private List<String> roomPromotion;

    @JsonProperty("CancelPolicies")
    private List<CancelPolicy> cancelPolicies;

    @JsonProperty("MealType")
    private String mealType;

    @JsonProperty("IsRefundable")
    private boolean isRefundable;

    @JsonProperty("Supplements")
    private List<List<Supplement>> supplements;

    @JsonProperty("WithTransfers")
    private boolean withTransfers;

    @JsonProperty("Amenities")
    private List<String> amenities;

    @JsonProperty("LastCancellationDeadline")
    private String lastCancellationDeadline;

    @JsonProperty("PriceBreakUp")
    private List<PriceBreakUp> priceBreakUps;
}

