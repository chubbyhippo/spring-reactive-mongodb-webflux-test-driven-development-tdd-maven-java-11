package com.example.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@Import(ReservationHttpConfiguration.class)
@SpringBootTest(properties = "server.port=0", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseClass {
    @MockBean
    private ReservationRepository repository;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() throws Exception {
	Mockito.when(this.repository.findAll())
		.thenReturn(Flux.just(
			new Reservation("1", "Jane"),
			new Reservation("2", "Joe")));

	RestAssured.baseURI = "http://localhost:" + this.port;
    }
}
