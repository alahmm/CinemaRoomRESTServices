package com.example.demo.buisnesslayout;

public class Ticket {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;
    public Ticket() {
        this.current_income = 0;
        this.number_of_available_seats = 81;
        this.number_of_purchased_tickets = 0;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }
}
