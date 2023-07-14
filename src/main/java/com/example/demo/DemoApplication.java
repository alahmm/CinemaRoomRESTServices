package com.example.demo;

import com.example.demo.buisnesslayout.Cinema;
import com.example.demo.buisnesslayout.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public Seat seat() {
		return new Seat();
	}
	@Bean
	public Cinema getCinema(@Autowired Seat seat) {
		List<Seat> seats = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				seat.setRow(i);
				seat.setColumn(j);
				seats.add(seat);
			}
		}
		return new Cinema(9, 9, seats);
	}



}
