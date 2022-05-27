package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
public class Part10ReactiveToBlocking {

//========================================================================================

	// TODO Return the user contained in that Mono
	// TODO Devuelve el usuario contenido en ese Mono
	User monoToValue(Mono<User> mono) {

		User user = mono.block();
		return user;
	}

//========================================================================================

	// TODO Return the users contained in that Flux
	// TODO Devuelve los usuarios contenidos en ese Flux
	Iterable<User> fluxToValues(Flux<User> flux) {

		Iterable<User> user = flux.toIterable();
		return user;
	}

}
