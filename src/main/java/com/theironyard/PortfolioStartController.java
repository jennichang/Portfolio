package com.theironyard;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class PortfolioStartController {

    @RequestMapping(path = "/")
    @ResponseBody
    String home() {
        return "Hello, world!";
    }

}