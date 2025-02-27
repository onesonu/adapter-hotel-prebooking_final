package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;
import java.util.List;

@Data
public class HotelBookRequest {
    private String bookingCode;
    private boolean isVoucherBooking;
    private String guestNationality;
    private String endUserIp;
    private int requestedBookingMode;
    private double netAmount;
    private PriceSummary priceSummary;
    private List<HotelRoomDetails> hotelRoomsDetails;
    private boolean isRefundable;
}
