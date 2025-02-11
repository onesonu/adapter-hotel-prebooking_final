package com.fastays.adapter_hotel_prebooking.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelResponseTbo {
    @JsonProperty("Status")
    private StatusDTO status;
    @JsonProperty("HotelResult")
    private List<HotelResultDTO> hotelResult;
    @JsonProperty("ValidationInfo")
    private ValidationInfoDTO validationInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class StatusDTO {
        @JsonProperty("Code")
        private int code;
        @JsonProperty("Description")
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class HotelResultDTO {
        @JsonProperty("HotelCode")
        private String hotelCode;
        @JsonProperty("Currency")
        private String currency;
        @JsonProperty("Rooms")
        private List<RoomDTO> rooms;
        @JsonProperty("RateConditions")
        private List<String> rateConditions;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class RoomDTO {
        @JsonProperty("Name")
        private List<String> name;
        @JsonProperty("BookingCode")
        private String bookingCode;
        @JsonProperty("Inclusion")
        private String inclusion;
        @JsonProperty("DayRates")
        private List<List<DayRateDTO>> dayRates;
        @JsonProperty("TotalFare")
        private double totalFare;
        @JsonProperty("TotalTax")
        private double totalTax;
        @JsonProperty("RoomPromotion")
        private List<String> roomPromotion;
        @JsonProperty("CancelPolicies")
        private List<CancelPolicyDTO> cancelPolicies;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class DayRateDTO {
        @JsonProperty("BasePrice")
        private double basePrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class CancelPolicyDTO {
        @JsonProperty("Index")
        private String index;
        @JsonProperty("FromDate")
        private String fromDate;
        @JsonProperty("ChargeType")
        private String chargeType;
        @JsonProperty("CancellationCharge")
        private int cancellationCharge;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ValidationInfoDTO {
        @JsonProperty("PanMandatory")
        private boolean panMandatory;
        @JsonProperty("PassportMandatory")
        private boolean passportMandatory;
    }
}
