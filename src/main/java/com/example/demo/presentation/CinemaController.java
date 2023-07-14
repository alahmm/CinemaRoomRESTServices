package com.example.demo.presentation;

import com.example.demo.buisnesslayout.Cinema;
import com.example.demo.buisnesslayout.Seat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class CinemaController {
    @Autowired
    private Cinema cinema;

    @GetMapping("/seats")
    public String getSeats() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(cinema);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    @PostMapping("/purchase")
    public ResponseEntity<String> placeStatus(@RequestBody Seat seat) throws JsonProcessingException {
        if (seat.getColumn() > 9 || seat.getRow() > 9 || seat.getRow() < 1 || seat.getColumn() < 1) {
            return new ResponseEntity<>("{\n" +
                    "    \"error\": \"The number of a row or a column is out of bounds!\"\n" +
                    "}"
                    , HttpStatus.BAD_REQUEST
            );
        }
        for (Seat seatNew : cinema.getListOfSeat()
        ) {
            if (seatNew.getRow() == seat.getRow() && seatNew.getColumn() == seat.getColumn()) {
                seat.setPrice(seatNew.getPrice());
                cinema.getListOfSeat().remove(seatNew);
                ObjectMapper objectMapper = new ObjectMapper();
                return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().
                        writeValueAsString(seat), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("{\n" +
                "    \"error\": \"The ticket has been already purchased!\"\n" +
                "}"
                ,HttpStatus.BAD_REQUEST
        );
    }

}

