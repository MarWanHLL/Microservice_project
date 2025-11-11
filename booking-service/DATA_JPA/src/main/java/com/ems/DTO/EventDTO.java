package com.ems.DTO;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventDTO {
    private Long id;
    private String title;
    private String date;
    private String location;

    // Getters & Setters
}