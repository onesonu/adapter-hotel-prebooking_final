package com.fastays.adapter_hotel_prebooking.repository;

import com.fastays.adapter_hotel_prebooking.dto.response.HotelBookingMngo;
import com.fastays.adapter_hotel_prebooking.dto.response.HotelBookingResponseMngo;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoDbRepository extends MongoRepository<HotelBookingResponseMngo, String> {
    // Method to find hotels by  booking code(single)
    @Query("{ 'bookingCode' : ?0 }")
    Optional<HotelBookingResponseMngo> findByBookingCode(String bookingCode);

}
