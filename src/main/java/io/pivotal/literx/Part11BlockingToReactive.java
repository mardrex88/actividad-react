package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.BlockingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Learn how to call blocking code from Reactive one with adapted concurrency strategy for
 * blocking code that produces or receives data.
 *
 * For those who know RxJava:
 *  - RxJava subscribeOn = Reactor subscribeOn
 *  - RxJava observeOn = Reactor publishOn
 *  - RxJava Schedulers.io <==> Reactor Schedulers.elastic
 *
 * @author Sebastien Deleuze
 * @see Flux#subscribeOn(Scheduler)
 * @see Flux#publishOn(Scheduler)
 * @see Schedulers
 */
public class Part11BlockingToReactive {

//========================================================================================

	// TODO Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run it with an elastic scheduler
	// TODO Crear un Flux para leer todos los usuarios del repositorio de bloqueo diferido hasta que se suscriba el flux, y ejecutarlo con un planificador elástico

	Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {

		Flux<User> result = Flux.defer(() ->
						Flux.fromIterable(repository.findAll()))
						.subscribeOn(Schedulers.elastic());
		return result;
	}

//========================================================================================

	// TODO Insert users contained in the Flux parameter in the blocking repository using an elastic scheduler and return a Mono<Void> that signal the end of the operation
	// TODO Insertar los usuarios contenidos en el parámetro Flux en el repositorio de bloqueo utilizando un planificador elástico y devolver un Mono<Void> que señale el final de la operación

	Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {

		Mono<Void> result = flux.publishOn(Schedulers.elastic())
				.doOnNext(u -> repository.save(u))
				.then();
		return result;
	}

}
