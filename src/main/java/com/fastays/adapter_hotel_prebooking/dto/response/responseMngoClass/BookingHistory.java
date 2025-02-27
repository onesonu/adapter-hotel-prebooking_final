package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingHistory {
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
