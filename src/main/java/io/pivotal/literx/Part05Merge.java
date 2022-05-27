package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 */
public class Part05Merge {

//========================================================================================

	// TODO Merge flux1 and flux2 values with interleave
	// TODO Combinar los valores de flux1 y flux2 con el intercalado
	Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {

		Flux<User> result  = flux1.mergeWith(flux2);
		return result;
	}

//========================================================================================

	// TODO Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
	// TODO Combinar los valores de flux1 y flux2 sin intercalar (valores de flux1 y luego valores de flux2)
	Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {

		Flux<User> result = flux1.concatWith(flux2);
		return result;
	}

//========================================================================================

	// TODO Create a Flux containing the value of mono1 then the value of mono2
	// TODO Crear un Flux que contenga el valor de mono1 y luego el valor de mono2
	Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {

		Flux<User> result = Flux.concat(mono1,mono2);
		return result;
	}

}
