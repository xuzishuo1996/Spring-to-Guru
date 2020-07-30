package guru.springframework.joke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:chuck-config.xml")   //for XML configuration
public class JokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JokeApplication.class, args);
    }

}
