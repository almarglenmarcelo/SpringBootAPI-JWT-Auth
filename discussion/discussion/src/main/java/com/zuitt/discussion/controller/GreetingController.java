package com.zuitt.discussion.controller;


import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/greeting")
public class GreetingController {



    @GetMapping("/hello")
    public String showName() {
        return "Hello World!";
    }

    @GetMapping("/hi")
//    http://localhost:8080/hello?name=Joe
    public String hi(@RequestParam(value="name", defaultValue = "User") String name){

        return String.format("Hi %s", name);
    }


//    Multiple Parameter
//    http://localhost:8080/friend?name=Joe&friend=Joanna
    @GetMapping("/friend")
    public String friend(@RequestParam(value="name", defaultValue = "Joe") String name,
                         @RequestParam(value="friend", defaultValue = "Jane") String friend) {
        return String.format("Hello %s! My name is %s.", friend, name);
    }


//    http://localhost:8080/hello/:wildcard
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return String.format("Nice to meet you %s!", name);
    }



//    @GetMapping("/hi/{name}")
//    public String hi(@PathVariable String name){
//        return "Hi " + name ;
//    }



}
