package com.fastays.adapter_hotel_prebooking.service.impl;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelResponseTbo;
import com.fastays.adapter_hotel_prebooking.service.interfaces.HotelPrebookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HotelPrebookingServiceImpl implements HotelPrebookingService {

    private static final Logger logger = LoggerFactory.getLogger(HotelPrebookingServiceImpl.class);

    @Value("${tektravels.api.url}")
    private String apiUrl;

    @Value("${tektravels.api.username}")
    private String apiUserName;

    @Value("${tektravels.api.secret}")
    private String apiSecret;

    private final RestTemplate restTemplate;

    @Override
    public HotelResponseTbo checkPrebooking(HotelRequest hotelRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(apiUserName, apiSecret); // Basic authentication
        HttpEntity<HotelRequest> entity = new HttpEntity<>(hotelRequest, headers);

        logger.info("Sending pre-booking request to API: {}", apiUrl);

        try {
            ResponseEntity<HotelResponseTbo> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, HotelResponseTbo.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Received response from API: {}", response.getBody());
                return response.getBody();
            } else {
                logger.error("Failed to fetch prebooking data. API response: {}", response);
                return createErrorResponse("Failed to fetch prebooking data");
            }
        } catch (Exception e) {
            logger.error("Exception while fetching pre-booking data", e);
            return createErrorResponse("Internal Server Error");
        }
    }

    private HotelResponseTbo createErrorResponse(String errorMessage) {
        HotelResponseTbo response = new HotelResponseTbo();
        response.setStatus(new HotelResponseTbo.StatusDTO(500, errorMessage));
        return response;
    }
}
