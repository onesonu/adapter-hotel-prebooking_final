package com.fastays.adapter_hotel_prebooking.controller;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.service.interfaces.HotelPrebookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http:localhost:9091/api/hotel-prebooing
@RestController
@RequestMapping("/api/hotel-prebooking")
@RequiredArgsConstructor
public class HotelPreBookingController {
    private final HotelPrebookingService hotelPreBookingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookHotel(@RequestBody HotelRequest hotelRequest) {
        try {
            ResponseEntity<?> bookingResponse = hotelPreBookingService.bookHotel(hotelRequest);
            return ResponseEntity.ok(bookingResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing hotel booking: " + e.getMessage());
        }
    }
}