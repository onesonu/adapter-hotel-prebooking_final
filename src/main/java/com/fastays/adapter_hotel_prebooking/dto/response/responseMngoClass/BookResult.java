package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;

@Data
public class BookResult {
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
