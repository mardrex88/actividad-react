package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

	// TODO Create a Flux of user from Flux of username, firstname and lastname.
	// TODO Crear un Flux de usuario a partir de Flux de nombre de usuario, nombre y apellido.
	Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {

		Flux<User> result = Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
				.map(fluxes -> new User(fluxes.getT1(), fluxes.getT2(), fluxes.getT3()));


		return result;
	}

//========================================================================================

	// TODO Return the mono which returns its value faster
	// TODO Devuelve el mono que devuelve su valor más rápido
	Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {

		Mono<User> result = Mono.first(mono1,mono2);
		return result;
	}

//========================================================================================

	// TODO Return the flux which returns the first value faster
	// TODO Devuelve el flujo que devuelve el primer valor más rápido
	Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {

		Flux<User> result = Flux.first(flux1,flux2);
		return result;
	}

//========================================================================================

	// TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
	// TODO Convertir la entrada Flux<User> en un Mono<Void> que represente la señal completa del flujo
	Mono<Void> fluxCompletion(Flux<User> flux) {

		Mono<Void> result = flux.then();
		return result;
	}

//========================================================================================

	// TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
	// TODO Devuelve un Mono válido de usuario para entrada nula y usuario no nulo (pista: los Streams Reactivos no aceptan valores nulos)
	Mono<User> nullAwareUserToMono(User user) {

		Mono<User> result = Mono.justOrEmpty(user);
		return result;
	}

//========================================================================================

	// TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
	// TODO Devuelve el mismo mono pasado como parámetro de entrada, espera que emita User.SKYLER cuando esté vacío
	Mono<User> emptyToSkyler(Mono<User> mono) {

		Mono<User> result =  mono.defaultIfEmpty(User.SKYLER);
		return result;
	}

//========================================================================================

	// TODO Convert the input Flux<User> to a Mono<List<User>> containing list of collected flux values
	// TODO Convertir la entrada Flux<User> en un Mono<List<User>> que contenga la lista de valores de flujo recogidos
	Mono<List<User>> fluxCollection(Flux<User> flux) {

		Mono<List<User>> result = flux.collectList();
		return result;
	}

}
