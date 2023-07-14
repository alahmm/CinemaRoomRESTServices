package com.example.demo.presentation;

import com.example.demo.buisnesslayout.Cinema;
import com.example.demo.buisnesslayout.Seat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {
    @Autowired
    private Cinema cinema;
    @GetMapping("/seats")
    public String getSeats() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().
                writeValueAsString(cinema);
    }

}

