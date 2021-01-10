package com.example.consumer;

import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:producer:+8080", stubsMode = LOCAL)

@AutoConfigureJson
public class ReservationClientTest {

	@Autowired
	private ReservationClient client;

	@Test
	public void getAllReservations() throws Exception {

		Flux<Reservation> resFlux = this.client.getAllReservation();

		List<String> names = Arrays.asList("Jane", "Joe");
		StepVerifier.create(resFlux)
				.expectNextMatches(
						reservation -> names.contains(reservation.getName()))
				.expectNextMatches(
						reservation -> names.contains(reservation.getName()))
				.verifyComplete();
	}
}
