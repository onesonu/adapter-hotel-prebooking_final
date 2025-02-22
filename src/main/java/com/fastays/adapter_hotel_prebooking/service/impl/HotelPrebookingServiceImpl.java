package com.fastays.adapter_hotel_prebooking.service.impl;

import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelBookingMngo;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelBookingResponseMngo;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelResponseTbo;
import com.fastays.adapter_hotel_prebooking.repository.MongoDbRepository;
import com.fastays.adapter_hotel_prebooking.service.interfaces.HotelPrebookingService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelPrebookingServiceImpl implements HotelPrebookingService {

    @Value("${tbo.api.url}")
    private String apiUrl;

    @Value("${tbo.api.username}")
    private String apiUserName;

    @Value("${tbo.api.secret}")
    private String apiSecret;

    private final RestTemplate restTemplate;
    private final Configuration freemarkerConfiguration;
    private final MongoDbRepository mongoDbRepository;

    @Override
    public ResponseEntity<?> bookHotel(HotelRequest hotelRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(apiUserName, apiSecret);
            HttpEntity<HotelRequest> requestEntity = new HttpEntity<>(hotelRequest, headers);

            // Corrected URL
            ResponseEntity<HotelResponseTbo> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, HotelResponseTbo.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                HotelResponseTbo hotelResponseTbo = response.getBody();
                HotelBookingResponseMngo fetchedHotels = null;
                if (hotelResponseTbo.getHotelResult() != null) {
                    fetchedHotels = fetchHotelUsingBookingCode(hotelResponseTbo);
                }
                String mappedFTL = maptoFtl(hotelResponseTbo, fetchedHotels);
                return ResponseEntity.ok(mappedFTL);

            } else {
                log.error("Failed to book hotel. Status: {}", response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).body("Booking failed: " + response.getStatusCode().toString());
            }

        } catch (Exception e) {
            log.error("Error during hotel booking: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing hotel booking: " + e.getMessage());
        }
    }

    public HotelBookingResponseMngo fetchHotelUsingBookingCode(HotelResponseTbo hotelResponseTbo) {
        HotelBookingResponseMngo hotelBookingResponseMngo = null;
        String bookingCode = null;

        List<HotelResponseTbo.HotelResult> hotelResults = hotelResponseTbo.getHotelResult();
        for (HotelResponseTbo.HotelResult rsult : hotelResults) {
            List<HotelResponseTbo.Room> roomslist = rsult.getRooms();
            for (HotelResponseTbo.Room room : roomslist) {
                bookingCode = room.getBookingCode();
            }
        }
        Optional<HotelBookingResponseMngo> result = mongoDbRepository.findByBookingCode(bookingCode);
        if (result.isPresent()) {
            hotelBookingResponseMngo = result.get();
            return hotelBookingResponseMngo;
        } else {
            log.info(" Data not present in the MongoDb with provided Booking ID");
            return null;
        }

    }
    public String maptoFtl(HotelResponseTbo hotelResponseTbo, HotelBookingResponseMngo hotelBookingResponseMngo) {
        try {
            if (hotelResponseTbo.getHotelResult() != null || hotelBookingResponseMngo != null) {

                Map<String, Object> mapped = new HashMap<>();

                mapped.put("resultsTbo", hotelResponseTbo);
                mapped.put("resultsMngo", hotelBookingResponseMngo);
                //mapped (both responses are present in the name ogf string)

                Template template = freemarkerConfiguration.getTemplate("hotel_response.ftl");
                return FreeMarkerTemplateUtils.processTemplateIntoString(template, mapped);
            } else {
                log.info("Not Mapping with FTL");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

