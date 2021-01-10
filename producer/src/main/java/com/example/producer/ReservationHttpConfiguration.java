package com.example.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ReservationHttpConfiguration {

	@Bean
	RouterFunction<ServerResponse> routes(ReservationRepository repository) {
		return RouterFunctions.route()
				.GET("/reservations",
						serverRequest -> ServerResponse.ok()
								.body(repository.findAll(), Reservation.class))
				.build();
	}

}
