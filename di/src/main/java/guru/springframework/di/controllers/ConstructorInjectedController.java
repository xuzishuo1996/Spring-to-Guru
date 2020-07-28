package guru.springframework.di.controllers;

import guru.springframework.di.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//most preferred method
@Controller
public class ConstructorInjectedController {

    //@Autowired    //optional on constructor injection
    private final GreetingService greetingService;

    public ConstructorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}
