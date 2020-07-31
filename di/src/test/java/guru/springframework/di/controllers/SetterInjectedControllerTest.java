package guru.springframework.di.controllers;

import guru.springframework.di.services.ConstructorGreetingService;
import guru.springframework.di.services.SetterGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//mimick what the Spring Framework will do
class SetterInjectedControllerTest {

    SetterInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new SetterInjectedController();

        controller.setGreetingService(new SetterGreetingService());
    }

    @Test
    void getGreeting() {
        System.out.println(controller.sayHello());
    }
}