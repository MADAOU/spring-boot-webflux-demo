package com.neusoft.bs.demo;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(final String[] args) {

        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // connectableFlux
        // final Flux<Long> flux = Flux.interval(Duration.ofMillis(1000)).take(10).publish().autoConnect();

        // flux.subscribe();
        // try {
        //     Thread.sleep(4000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // flux
        // // .map(i->i/i-4)
        // .toStream()
        // .forEach(System.out::println);
        // .subscribe(System.out::println);
        // .toStream()
        // .forEach(System.out::println);
        
        // subscribe(System.out::println);

        // checkpoint
        // Flux.just(1, 2, 0)
        //         // .map(i-> i+1/i)
        //         .concatWith(Flux.just(3, 4)).log().checkpoint().subscribe(System.out::println);

        // retry
        // Flux.just(1, 2, 3)
        // .map(num -> {
        // if (num == 3) {
        // throw new IllegalArgumentException(String.format("num is %s", num));
        // }

        // return num;
        // })
        // .concatWith(Mono.error(new ResourceNotFoundException()))
        // .retry(1)
        // .subscribe(System.out::println, System.err::println);

        // subscribe for success and error
        // Flux.just(1, 2, 3).map(num->{
        // if(num == 3) {
        // throw new IllegalArgumentException(String.format("num is %s", num));
        // }

        // return num;
        // })
        // .onErrorResume(e-> {
        // return Mono.just(-1);
        // // return Mono.empty();
        // // return Mono.never();
        // })
        // .concatWith(Mono.error(new ResourceNotFoundException()))
        // .onErrorReturn(-2)
        // // .onErrorContinue((e, i)-> {
        // // // System.err.println(String.format("onErrorContinue: %s, e: %s", i, e));
        // // })
        // .concatWith(Flux.just(4, 5))
        // // .doOnError(e->{
        // // System.err.println("error!");
        // // })
        // .subscribe(System.out::println,System.err::println);
        // // .subscribe(System.out::println);

        // combineLatest
        // Flux.combineLatest(Arrays::toString,
        // Flux.interval(Duration.ofMillis(150)).map(p -> String.format("1:%s", p)),
        // Flux.interval(Duration.ofMillis(50), Duration.ofMillis(150)).map(p ->
        // String.format("2:%s", p)),
        // Flux.interval(Duration.ofMillis(100), Duration.ofMillis(150)).map(p ->
        // String.format("3:%s", p)))
        // .take(5).toStream().forEach(System.out::println);

        // filter
        // Flux.range(1, 10).filter(x->x%2==0).subscribe(System.out::println);

        // //buffer
        // Flux.range(1, 10).buffer(5).subscribe(System.out::println);

        // TODO why toStream しないと、itemを取得できない？
        // Flux.interval(Duration.ofMillis(100)).buffer(Duration.ofMillis(1000)).take(2).toStream().forEach(p->{
        // System.out.println(p);
        // });

        // //window
        // Flux.range(1, 10).window(5).subscribe(System.out::println);

        // zipwith
        // Flux.just(1, 2, 5).zipWith(Flux.just(3, 4)).map(p -> String.format("%s, %s",
        // p.getT1(), p.getT2()))
        // .subscribe(System.out::println);
        // Flux.just(1, 2).zipWith(Flux.just(3, 4), (x, y) -> String.format("%s, %s", x,
        // y))
        // .subscribe(System.out::println);

        // range and reduceWith
        // Flux.range(1, 10).reduceWith(() -> 100, (x, y) -> {
        // System.out.println(String.format("x:%s, y:%s", x, y));
        // return x + y;
        // }).subscribe(System.out::println);

        // Flux.range(1, 10).reduce((x,y)->{
        // System.out.println(String.format("x:%s, y:%s", x, y));
        // return x + y;
        // }).subscribe(System.out::println);

        // merge and mergeSequential
        // Flux.mergeSequential(Flux.interval(Duration.ZERO,
        // Duration.ofMillis(1000)).take(5),
        // Flux.interval(Duration.ofMillis(50),
        // Duration.ofMillis(1000)).take(5)).toStream().forEach(System.out::println);

        // Flux.merge(Flux.interval(Duration.ZERO, Duration.ofMillis(1000)).take(5),
        // Flux.interval(Duration.ofMillis(50),
        // Duration.ofMillis(1000)).take(5)).toStream().forEach(System.out::println);

        // flatMap and flatMapSequential
        // Flux.just(5, 10).flatMap(x -> Flux.interval(Duration.ofMillis(x * 10),
        // Duration.ofMillis(1000)).take(x))
        // .toStream().forEach(System.out::println);

        // Flux.just(5, 10)
        // .flatMapSequential(x -> Flux.interval(Duration.ofMillis(x * 10),
        // Duration.ofMillis(1000)).take(x))
        // .toStream().forEach(System.out::println);

        // concatMap
        // Flux.just(5, 10).concatMap(x -> Flux.interval(Duration.ofMillis(x * 10),
        // Duration.ofMillis(1000)).take(x))
        // .toStream().forEach(System.out::println);

        // Flux.just(5, 10)
        // .toStream().forEach(System.out::println);

    }

}