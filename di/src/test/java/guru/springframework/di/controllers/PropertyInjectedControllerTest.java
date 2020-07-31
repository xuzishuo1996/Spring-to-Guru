package guru.springframework.di.controllers;

import guru.springframework.di.services.ConstructorGreetingService;
import guru.springframework.di.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//mimick what the Spring Framework will do: least preferred method
class PropertyInjectedControllerTest {

    PropertyInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new PropertyInjectedController();

        controller.greetingServiceImpl = new GreetingServiceImpl();
    }

    @Test
    void getGreeting() {
        System.out.println(controller.sayHello());
    }
}