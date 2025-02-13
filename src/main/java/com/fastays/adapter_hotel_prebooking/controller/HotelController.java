package com.fastays.adapter_hotel_prebooking.controller;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelResponseTbo;
import com.fastays.adapter_hotel_prebooking.service.interfaces.HotelPrebookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

            // Call the service method that returns ResponseEntity<String>
            ResponseEntity<String> responseEntity = hotelPrebookingService.checkPrebooking(hotelRequest);

            // Check if the response is OK and extract the response body
            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
                String jsonResponse = responseEntity.getBody();

                // Convert the JSON string response back to a HotelResponseTbo object
                HotelResponseTbo hotelResponse = parseJsonToHotelResponse(jsonResponse);

                // If the hotelResponse is valid, return it
                if (hotelResponse != null && hotelResponse.getStatus() != null && hotelResponse.getStatus().getCode() != 500) {
                    return new ResponseEntity<>(hotelResponse, HttpStatus.OK);
                } else {
                    logger.error("Error during pre-booking, response: {}", hotelResponse);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(createErrorResponse("Unexpected error from external API"));
                }
            } else {
                // Handle error responses from the service layer
                logger.error("Error response from service: {}", responseEntity.getBody());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(createErrorResponse("Failed to fetch pre-booking details"));
            }

        } catch (Exception e) {
            logger.error("Error occurred during pre-booking", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("Internal Server Error"));
        }
    }

    private HotelResponseTbo parseJsonToHotelResponse(String jsonResponse) {
        try {
            // Use your existing ObjectMapper to convert the JSON string into HotelResponseTbo
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, HotelResponseTbo.class);
        } catch (Exception e) {
            logger.error("Error parsing JSON response", e);
            return createErrorResponse("Failed to parse response");
        }
    }

    private HotelResponseTbo createErrorResponse(String errorMessage) {
        HotelResponseTbo response = new HotelResponseTbo();
        HotelResponseTbo.StatusDTO status = new HotelResponseTbo.StatusDTO(500, errorMessage);
        response.setStatus(status);
        return response;
    }
}
