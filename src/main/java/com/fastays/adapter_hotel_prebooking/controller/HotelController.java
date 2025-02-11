package com.fastays.adapter_hotel_prebooking.controller;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelResponseTbo;
import com.fastays.adapter_hotel_prebooking.service.interfaces.HotelPrebookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private final HotelPrebookingService hotelPrebookingService;

    @PostMapping("/check")
    public ResponseEntity<HotelResponseTbo> checkPrebooking(@RequestBody HotelRequest hotelRequest) {
        try {
            logger.info("Received pre-booking request: {}", hotelRequest);

            if (hotelRequest.getBookingCode() == null || hotelRequest.getPaymentMode() == null) {
                logger.error("Invalid request: missing required fields");
                return ResponseEntity.badRequest().body(createErrorResponse("Missing required fields"));
            }

            HotelResponseTbo hotelResponse = hotelPrebookingService.checkPrebooking(hotelRequest);

            if (hotelResponse != null && hotelResponse.getStatus() != null && hotelResponse.getStatus().getCode() != 500) {
                return new ResponseEntity<>(hotelResponse, HttpStatus.OK);
            } else {
                logger.error("Error during pre-booking, response: {}", hotelResponse);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(createErrorResponse("Unexpected error from external API"));
            }

        } catch (Exception e) {
            logger.error("Error occurred during pre-booking", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("Internal Server Error"));
        }
    }

    private HotelResponseTbo createErrorResponse(String errorMessage) {
        HotelResponseTbo response = new HotelResponseTbo();
        HotelResponseTbo.StatusDTO status = new HotelResponseTbo.StatusDTO(500, errorMessage);
        response.setStatus(status);
        return response;
    }
}
