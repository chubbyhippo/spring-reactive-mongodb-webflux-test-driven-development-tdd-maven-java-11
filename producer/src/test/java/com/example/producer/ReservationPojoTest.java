package com.example.producer;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ReservationPojoTest {
	@Test
	public void create() throws Exception {
		Reservation reservation = new Reservation("1", "Jane");
		assertThat(reservation.getName()).isEqualTo("Jane");
		MatcherAssert.assertThat(reservation.getName(), Matchers.equalToIgnoringCase("jane"));
		MatcherAssert.assertThat(reservation.getName(), new BaseMatcher<Object>() {

			@Override
			public boolean matches(Object actual) {
				return Character.isUpperCase(((String) actual).charAt(0));
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("the name should be valid uppercase");
			}
		});
		
		assertThat(reservation.getName()).isEqualToIgnoringCase("jane");
		
	}
}
