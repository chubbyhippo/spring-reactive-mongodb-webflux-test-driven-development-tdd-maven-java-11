package com.example.producer;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ReservationEntityTest {

	@Autowired
	private ReactiveMongoTemplate template;

	@Test
	public void persist() throws Exception {
		Reservation reservation = new Reservation(null, "Jane");
		Mono<Reservation> save = this.template.save(reservation);
		StepVerifier.create(save)
				.expectNextMatches(new Predicate<Reservation>() {

					@Override
					public boolean test(Reservation reservation) {
						return reservation.getName().equalsIgnoreCase("Jane")
								&& reservation.getId() != null;
					}
				}).verifyComplete();
	}
}
