package com.fastays.adapter_hotel_prebooking.dto.response;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @Data
    public static class HotelBookRequest {
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

    @Data
    public static class PriceSummary {
        private double netAmount;
        private double taxesAndOtherCharges;
        private double totalAmountBeforeDiscount;
        private boolean isOfferApplied;
        private double discountInPercentage;
        private double discountedPrice;
        private double netAmountAfterDiscount;
        private double totalAmountAfterDiscount;
    }

    @Data
    public static class HotelRoomDetails {
        private List<HotelPassenger> hotelPassenger;
    }

    @Data
    public static class HotelPassenger {
        private String title;
        private String firstName;
        private String middleName;
        private String lastName;
        private String email;
        private int paxType;
        private boolean leadPassenger;
        private int age;
        private String phoneno;
        private int paxId;
    }

    @Data
    public static class BookResult {
        private boolean voucherStatus;
        private int responseStatus;
        private ErrorInfo error;
        private String traceId;
        private int status;
        private String hotelBookingStatus;
        private String invoiceNumber;
        private String confirmationNo;
        private String bookingRefNo;
        private String bookingId;
        private boolean isPriceChanged;
        private boolean isCancellationPolicyChanged;
        private String fastaysId;
    }

    @Data
    public static class ErrorInfo {
        private int errorCode;
        private String errorMessage;
    }

    @Data
    public static class BookDetailsResponse {
        private BookingDetailResult bookingDetailResult;
    }

    @Data
    public static class BookingDetailResult {
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

    @Data
    public static class BookingHistory {
        private int bookingId;
        private int createdBy;
        private String createdByName;
        private LocalDateTime createdOn;
        private int eventCategory;
        private int lastModifiedBy;
        private String lastModifiedByName;
        private LocalDateTime lastModifiedOn;
        private String remarks;
    }
}
