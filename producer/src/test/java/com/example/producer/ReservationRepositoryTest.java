package com.example.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository repository;

	@Test
	public void query() throws Exception {
		// delete everything in the db
		// write 4 records
		// then query by name and assert count

		Flux<Reservation> resFlux = this.repository.deleteAll()
				.thenMany(Flux.just("A", "B", "C", "C")
						.map(name -> new Reservation(null, name))
						.flatMap(r -> this.repository.save(r)))
				.thenMany(this.repository.findByName("C"));

		StepVerifier.create(resFlux).expectNextCount(2).verifyComplete();

	}
	
}
