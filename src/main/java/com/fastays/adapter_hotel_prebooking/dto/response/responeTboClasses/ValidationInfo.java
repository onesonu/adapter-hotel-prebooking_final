package com.fastays.adapter_hotel_prebooking.dto.response.responeTboClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationInfo {
    @JsonProperty("PanMandatory")
    private boolean panMandatory;

    @JsonProperty("PassportMandatory")
    private boolean passportMandatory;

    @JsonProperty("CorporateBookingAllowed")
    private boolean corporateBookingAllowed;

    @JsonProperty("PanCountRequired")
    private int panCountRequired;

    @JsonProperty("SamePaxNameAllowed")
    private boolean samePaxNameAllowed;

    @JsonProperty("SpaceAllowed")
    private boolean spaceAllowed;

    @JsonProperty("SpecialCharAllowed")
    private boolean specialCharAllowed;

    @JsonProperty("PaxNameMinLength")
    private int paxNameMinLength;

    @JsonProperty("PaxNameMaxLength")
    private int paxNameMaxLength;

    @JsonProperty("CharLimit")
    private boolean charLimit;

    @JsonProperty("PackageFare")
    private boolean packageFare;

    @JsonProperty("PackageDetailsMandatory")
    private boolean packageDetailsMandatory;

    @JsonProperty("DepartureDetailsMandatory")
    private boolean departureDetailsMandatory;

    @JsonProperty("GstAllowed")
    private boolean gstAllowed;
}

