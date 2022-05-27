package io.pivotal.literx;

import reactor.core.publisher.Mono;

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
public class Part02Mono {

//========================================================================================

	// TODO Return an empty Mono
	Mono<String> emptyMono() {
		Mono<String> result = Mono.empty();
		return result;
	}

//========================================================================================

	// TODO Return a Mono that never emits any signal
	Mono<String> monoWithNoSignal() {

		Mono<String> result = Mono.never();
		return result;
	}

//========================================================================================

	// TODO Return a Mono that contains a "foo" value
	Mono<String> fooMono() {
		Mono<String> result = Mono.just("foo");
		return result;
	}

//========================================================================================

	// TODO Create a Mono that emits an IllegalStateException
	Mono<String> errorMono() {
		Mono<String> result = Mono.error(new IllegalStateException());
		return result;
	}

}
