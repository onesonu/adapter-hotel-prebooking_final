package com.fastays.adapter_hotel_prebooking.service.interfaces;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import org.springframework.http.ResponseEntity;

public interface HotelPrebookingService {
    ResponseEntity<?> bookHotel(HotelRequest hotelRequest);
}
