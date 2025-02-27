package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class BookingDetailResult {
    private boolean voucherStatus;
    private int responseStatus;
    private ErrorInfo error;
    private String traceId;
    private int status;
    private String hotelBookingStatus;
    private String confirmationNo;
    private String bookingRefNo;
    private int bookingId;
    private boolean isPriceChanged;
    private boolean isCancellationPolicyChanged;
    private List<HotelRoomDetails> hotelRoomsDetails;
    private String agentRemarks;
    private List<BookingHistory> bookingHistory;
    private String bookingSource;
    private String guestNationality;
    private String hotelPolicyDetail;
    private double invoiceAmount;
    private LocalDateTime invoiceCreatedOn;
    private String invoiceNo;
    private boolean isCorporate;
    private Map<String, Object> validationInfo;
    private String hotelCode;
    private int hotelId;
    private String hotelName;
    private String tBOHotelCode;
    private int starRating;
    private String addressLine1;
    private String addressLine2;
    private String countryCode;
    private String latitude;
    private String longitude;
    private String city;
    private int cityId;
    private LocalDateTime checkInDate;
    private LocalDateTime initialCheckInDate;
    private LocalDateTime checkOutDate;
    private LocalDateTime initialCheckOutDate;
    private LocalDateTime lastCancellationDate;
    private LocalDateTime lastVoucherDate;
    private int noOfRooms;
    private LocalDateTime bookingDate;
    private String specialRequest;
    private boolean isDomestic;
    private boolean bookingAllowedForRoamer;
}
