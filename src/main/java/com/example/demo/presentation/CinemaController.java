package com.example.demo.presentation;

import com.example.demo.buisnesslayout.Cinema;
import com.example.demo.buisnesslayout.Seat;


import com.example.demo.buisnesslayout.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class CinemaController {
    @Autowired
    private Cinema cinema;
    public Map<Seat, UUID> map = new HashMap<>();

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

                User user = new User();
                user.setSeat(seat);
                UUID token = Token.generateType1UUID();
                user.setToken(token);
                map.put(seat, token);
                return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().
                        writeValueAsString(user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("{\n" +
                "    \"error\": \"The ticket has been already purchased!\"\n" +
                "}"
                ,HttpStatus.BAD_REQUEST
        );
    }
    @PostMapping("/return")
    public ResponseEntity<String> ticketStatus(@RequestBody User user) throws JsonProcessingException {
        ReturnedTicket returnedTicket = new ReturnedTicket();
        boolean isUsed = false;
        for (Map.Entry<Seat, UUID> newMap : map.entrySet()
             ) {
            if (newMap.getValue().equals(user.getToken())) {
                cinema.getListOfSeat().add(newMap.getKey());
                returnedTicket.setReturned_ticket(newMap.getKey());
                isUsed = true;
            }
        }
        if (isUsed) {
            ObjectMapper objectMapper = new ObjectMapper();
            while (map.containsValue(user.getToken())) {
                map.remove(returnedTicket.getReturned_ticket(), user.getToken());
            }
            return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(returnedTicket), HttpStatus.OK);
        }
        return new ResponseEntity<>("{\n" +
                "    \"error\": \"Wrong token!\"\n" +
                "}"
                ,HttpStatus.BAD_REQUEST
        );
    }

}

