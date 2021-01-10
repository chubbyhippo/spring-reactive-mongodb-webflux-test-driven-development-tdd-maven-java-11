package com.example.consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

	private final WebClient client;

	public Flux<Reservation> getAllReservation() {
		return this.client.get().uri("http://localhost:8080/reservations").retrieve().bodyToFlux(Reservation.class);

	}

}
