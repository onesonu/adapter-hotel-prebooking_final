package com.fastays.adapter_hotel_prebooking.dto.response.responseMngoClass;

import lombok.Data;

@Data
public class HotelPassenger {
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private int paxType;
    private boolean leadPassenger;
    private int age;
    private String phoneno;
    private int paxId;
}
