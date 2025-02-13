package com.fastays.adapter_hotel_prebooking.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastays.adapter_hotel_prebooking.dto.request.HotelRequest;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelResponseTbo;
import com.fastays.adapter_hotel_prebooking.service.interfaces.HotelPrebookingService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private final Configuration freemarkerConfig;
    private final ObjectMapper objectMapper; // Used for JSON parsing

    @Override
    public ResponseEntity<String> checkPrebooking(HotelRequest hotelRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(apiUserName, apiSecret);
        HttpEntity<HotelRequest> entity = new HttpEntity<>(hotelRequest, headers);

        try {
            ResponseEntity<HotelResponseTbo> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, HotelResponseTbo.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                HotelResponseTbo hotelResponseTbo = response.getBody();

                // Prepare the data model for FreeMarker
                Map<String, Object> model = new HashMap<>();
                model.put("status", hotelResponseTbo.getStatus());
                model.put("hotelResult", hotelResponseTbo.getHotelResult());
                model.put("validationInfo", hotelResponseTbo.getValidationInfo());

                // Load and process FreeMarker template
                Template template = freemarkerConfig.getTemplate("hotel_response.ftl");
                String jsonResponse = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

                // Convert the FreeMarker string output into a properly formatted JSON response
                String formattedJson = objectMapper.readTree(jsonResponse).toPrettyString();

                // Return JSON response with correct content type
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(formattedJson);
            }
        } catch (IOException | TemplateException e) {
            logger.error("Error processing FreeMarker template", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Template processing failed\"}");
        } catch (Exception e) {
            logger.error("Error fetching hotel prebooking details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Unexpected error occurred\"}");
        }

        // Return error response if API call fails
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\": \"Failed to fetch hotel prebooking details\"}");
    }
}
