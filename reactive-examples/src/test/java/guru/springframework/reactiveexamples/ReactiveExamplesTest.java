package guru.springframework.reactiveexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ReactiveExamplesTest {

    Person michael = new Person("Michael", "Weston");
    Person fiona = new Person("Fiona", "Glenanne");
    Person sam = new Person("Sam", "Axe");
    Person jesse = new Person("Jesse", "Porter");

    @Test
    public void monoTest() {
        //create new person mono
        Mono<Person> personMono = Mono.just(michael);

        //get person object from mono publisher
        //Since we are in a test, we instead block, waiting for the processing to finish
        Person person = personMono.block();

        //output name
        log.info(person.sayMyName());
    }

    @Test
    public void monoTransform() {
        Mono<Person> personMono = Mono.just(fiona);

        PersonCommand command = personMono
                .map(person -> new PersonCommand(person)).block();
//                .map(person -> {
//                    return new PersonCommand(person);
//                }).block();

        log.info(command.sayMyName());
    }

    @Test
    public void monoFilter() {
        Mono<Person> personMono = Mono.just(sam);

        Person samAxe = personMono
                .filter(person -> person.getFirstName().equalsIgnoreCase("foo"))
                .block();

        assertThrows(NullPointerException.class, () -> {
            log.info(samAxe.sayMyName()); //throws NPE
        });
    }

    @Test
    public void fluxTest() {

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestFilter() {

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.filter(person -> person.getFirstName().equals(fiona.getFirstName()))
                .subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestDelayNoOutput() {

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }

    @Test
    public void fluxTestFilterDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .filter(person -> person.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }
}
