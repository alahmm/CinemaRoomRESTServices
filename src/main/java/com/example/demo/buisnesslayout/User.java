package com.example.demo.buisnesslayout;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {
    private UUID token;
    @JsonProperty("ticket")
    private Seat seat;

    public UUID getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
