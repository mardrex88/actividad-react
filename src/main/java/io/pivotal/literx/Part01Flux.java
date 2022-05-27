package io.pivotal.literx;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

//========================================================================================

	// TODO Return an empty Flux
	Flux<String> emptyFlux() {
			Flux<String> flux = Flux.empty();
		return flux;
	}

//========================================================================================

	// TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
	Flux<String> fooBarFluxFromValues() {

		Flux<String> result = Flux.just("foo","bar");
		return result;
	}

//========================================================================================

	// TODO Create a Flux from a List that contains 2 values "foo" and "bar"
	Flux<String> fooBarFluxFromList() {

		List<String> list = Arrays.asList("foo","bar");
		return Flux.fromIterable(list);
	}

//========================================================================================

	// TODO Create a Flux that emits an IllegalStateException
	Flux<String> errorFlux() {

		Flux<String> error = Flux.error(new IllegalStateException());
		return  error;
	}

//========================================================================================

		// TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
	Flux<Long> counter() {

		Flux<Long> result = Flux.interval(Duration.ofMillis(100)).take(10);
		return result;
	}

}
