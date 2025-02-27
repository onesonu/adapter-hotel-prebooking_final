package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "hotel_booking")
public class HotelBookingResponseMngo {

    @Id
    private String id;
    private String bookingStatus;
    private String razorPayOrderId;
    private String razorPayOrderStatus;
    private String razorPayOrderMessage;
    private String userId;
    private String bookingCode;
    private boolean isVoucherBooking;
    private int requestedBookingMode;
    private double netAmount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private HotelBookRequest hotelBookRequest;
    private BookResult bookResult;
    private BookDetailsResponse bookDetailsResponse;
    private boolean isRefundable;
}
