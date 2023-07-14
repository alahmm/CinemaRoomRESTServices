package com.example.demo.buisnesslayout;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cinema {
    private int total_rows;
    private int total_columns;
    @JsonProperty("available_seats")
    private List<Seat> listOfSeat;

    public Cinema(int total_rows, int total_columns, List<Seat> listOfSeat) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.listOfSeat = listOfSeat;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getListOfSeat() {
        return listOfSeat;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setListOfSeat(List<Seat> listOfSeat) {
        this.listOfSeat = listOfSeat;
    }
}
