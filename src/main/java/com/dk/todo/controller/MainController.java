package com.dk.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/main")
@RestController
public class MainController {


    @GetMapping("/a")
    public String getMain() {
        return "abvcd";
    }
}
