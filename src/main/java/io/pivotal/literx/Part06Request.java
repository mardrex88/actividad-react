package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

	ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
	// TODO Crear un StepVerifier que solicite inicialmente todos los valores y esperar que se reciban 4 valores
	StepVerifier requestAllExpectFour(Flux<User> flux) {
		StepVerifier stepVerifier = StepVerifier.create(flux)
				.expectNextCount(4)
				.expectComplete();
		return stepVerifier;
	}

//========================================================================================

	// TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE then stops verifying by cancelling the source
	StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {

		StepVerifier stepVerifier = StepVerifier.create(flux,1)
				.expectNext(User.SKYLER)
				.thenRequest(1)
				.expectNext(User.JESSE)
				.thenCancel();
		return stepVerifier;
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
	// TODO Devuelve un Flux con todos los usuarios almacenados en el repositorio que imprime automáticamente los registros de todas las señales de Reactive Streams
	Flux<User> fluxWithLog() {

		Flux<User> result = repository.findAll().log();
		return result;
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
	// TODO Devuelve un Flux con todos los usuarios almacenados en el repositorio que imprime "Starring:" al suscribirse, "firstname lastname" para todos los valores y "The end!" al completarse
	Flux<User> fluxWithDoOnPrintln() {

		Flux<User> result = repository.findAll().doOnSubscribe(v -> System.out.println("Starring:"))
				.doOnNext(user -> System.out.println(user.getFirstname().concat(" ").concat(user.getLastname())))
				.doOnComplete(() -> System.out.println("The end!"));
		return result;
	}

}
