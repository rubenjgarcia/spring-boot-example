package es.rubenjgarcia.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class TestController {

    @RequestMapping
    public Response get() {
        return new Response("Hello World!");
    }
}
