/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.literx;

import java.util.concurrent.CompletableFuture;

import io.pivotal.literx.domain.User;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to adapt from/to RxJava 3 Observable/Single/Flowable and Java 8+ CompletableFuture.
 *
 * Mono and Flux already implements Reactive Streams interfaces so they are natively
 * Reactive Streams compliant + there are {@link Mono#from(Publisher)} and {@link Flux#from(Publisher)}
 * factory methods.
 *
 * For RxJava 3, you should not use Reactor Adapter but only RxJava 3 and Reactor Core.
 *
 * @author Sebastien Deleuze
 */
public class Part09Adapt {

//========================================================================================

	// TODO Adapt Flux to RxJava Flowable
	// TODO Adaptar Flux a RxJava Flowable
	Flowable<User> fromFluxToFlowable(Flux<User> flux) {

		Flowable<User> result = Flowable.fromPublisher(flux);
		return result;
	}

	// TODO Adapt RxJava Flowable to Flux
	// TODO Adaptar RxJava Flowable a Flux
	Flux<User> fromFlowableToFlux(Flowable<User> flowable) {

		Flux<User> result = Flux.from(flowable);
		return result;
	}

//========================================================================================

	// TODO Adapt Flux to RxJava Observable
	// TODO Adaptar Flux a RxJava Observable
	Observable<User> fromFluxToObservable(Flux<User> flux) {

		Observable<User> result = Observable.fromPublisher(flux);
		return result;
	}

	// TODO Adapt RxJava Observable to Flux
	// TODO Adaptar RxJava Observable a Flux
	Flux<User> fromObservableToFlux(Observable<User> observable) {

		Flux<User> result = Flux.from(observable.toFlowable(BackpressureStrategy.BUFFER));
		return result;
	}

//========================================================================================

	// TODO Adapt Mono to RxJava Single
	// TODO Adaptar Mono a RxJava Single
	Single<User> fromMonoToSingle(Mono<User> mono) {

		Single<User> result = Single.fromPublisher(mono);
		return result;
	}

	// TODO Adapt RxJava Single to Mono
	// TODO Adaptar RxJava Single a Mono
	Mono<User> fromSingleToMono(Single<User> single) {

		Mono<User> result =Mono.from(single.toFlowable());
		return result;
	}

//========================================================================================

	// TODO Adapt Mono to Java 8+ CompletableFuture
	// TODO Adaptar Mono a Java 8+ CompletableFuture
	CompletableFuture<User> fromMonoToCompletableFuture(Mono<User> mono) {

		CompletableFuture<User> result = mono.toFuture();
		return result;
	}

	// TODO Adapt Java 8+ CompletableFuture to Mono
	// TODO Adaptar Java 8+ CompletableFuture a Mono
	Mono<User> fromCompletableFutureToMono(CompletableFuture<User> future) {

		Mono<User> result = Mono.fromFuture(future);
		return result;
	}

}
