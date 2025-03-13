package spring.alishev.mvcapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello_world")
    public String sayHello() {
        return"hello_world";
    }
}
