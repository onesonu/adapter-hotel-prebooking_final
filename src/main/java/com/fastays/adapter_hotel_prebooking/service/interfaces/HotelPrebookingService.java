package com.fastays.adapter_hotel_prebooking.service.interfaces;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelResponseTbo;

public interface HotelPrebookingService {
    HotelResponseTbo checkPrebooking(HotelRequest hotelRequest);
}
