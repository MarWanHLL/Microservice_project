package com.ems.Client;


import com.ems.DTO.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "event-service")
public interface EventClient {

    @GetMapping("/events")
    List<EventDTO> getAllEvents();

    @GetMapping("/events/{id}")
    EventDTO getEventById(@PathVariable("id") Long id);
}