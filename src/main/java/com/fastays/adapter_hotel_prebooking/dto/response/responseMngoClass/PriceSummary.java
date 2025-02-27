package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;

@Data
public class PriceSummary {
    private double netAmount;
    private double taxesAndOtherCharges;
    private double totalAmountBeforeDiscount;
    private boolean isOfferApplied;
    private double discountInPercentage;
    private double discountedPrice;
    private double netAmountAfterDiscount;
    private double totalAmountAfterDiscount;
}

