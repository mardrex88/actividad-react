package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

	// TODO Capitalize the user username, firstname and lastname
	// TODO Poner en mayúsculas el nombre de usuario, el nombre y el apellido
	Mono<User> capitalizeOne(Mono<User> mono) {

	Mono<User> result = mono.map(user ->
				new User(user.getUsername().toUpperCase(),
						user.getFirstname().toUpperCase(),
						user.getLastname().toUpperCase()
				));
		return result;
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName
	// TODO Poner en mayúsculas el nombre de usuario, el nombre y el apellido
	Flux<User> capitalizeMany(Flux<User> flux) {

		Flux<User> result = flux.map(user ->
				new User(user.getUsername().toUpperCase(),
						user.getFirstname().toUpperCase(),
						user.getLastname().toUpperCase())
				);
		return result;
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	// TODO Poner en mayúsculas el nombre de usuario, el nombre y el apellido usando #asyncCapitalizeUser
	Flux<User> asyncCapitalizeMany(Flux<User> flux) {

		Flux<User> result = flux.flatMap(user -> asyncCapitalizeUser(user));
		return result;
	}

	Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
