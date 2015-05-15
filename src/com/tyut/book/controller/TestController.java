package com.tyut.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tc")
public class TestController {

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public void test(@RequestParam(value = "name", defaultValue= "") String name) {
        System.out.println(name);
    }

}
