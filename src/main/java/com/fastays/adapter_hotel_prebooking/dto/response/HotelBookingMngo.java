package com.fastays.adapter_hotel_prebooking.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "hotel_booking")
public class HotelBookingMngo {
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

    private Instant createdDate;

    private Instant updatedDate;

    private HotelBookRequest hotelBookRequest;

    private PriceSummary priceSummary;

    private List<HotelRoomDetails> hotelRoomsDetails;

    private HotelDetails hotelDetails;

    private SearchInfo searchInfo;

    private BookResult bookResult;

    private ErrorResponse error;

    private BookingDetailsResponse bookDetailsResponse;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HotelBookRequest {

        private String bookingCode;

        private boolean isVoucherBooking;

        private String guestNationality;

        private String endUserIp;

        private int requestedBookingMode;

        private double netAmount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PriceSummary {

        private double netAmount;

        private double taxesAndOtherCharges;

        private double totalAmountBeforeDiscount;

        private String discountCode;

        private String type;

        private boolean isOfferApplied;

        private double discountInPercentage;

        private double discountedPrice;

        private double netAmountAfterDiscount;

        private double totalAmountAfterDiscount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HotelRoomDetails {

        private List<HotelPassenger> hotelPassenger;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
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

        private boolean isRefundable;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HotelDetails {

        private Rooms rooms;
        private List<String> hotelPolicyDetail;

        private HotelInfo hotelInfo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Rooms {

        private double taxesAndOtherCharges;

        private double totalAmount;

        private double totalFare;

        private double totalTax;

        private double netAmount;

        private double netTax;

        private boolean refundable;

        private boolean withTransfers;

        private String hotelCode;

        private String currency;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HotelInfo {

        private String hotelName;

        private String rating;

        private String location;

        private String map;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SearchInfo {

        private String checkinDate;

        private String checkOutDate;

        private int numberOfRooms;

        private int adultCount;

        private int childrenCount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BookResult {

        private boolean voucherStatus;

        private int responseStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ErrorResponse {

        private int errorCode;

        private String errorMessage;


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
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BookingDetailsResponse {

        private BookingDetailResult bookingDetailResult;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BookingDetailResult {

        private boolean voucherStatus;

        private int responseStatus;

        private ErrorResponse error;
    }
}
