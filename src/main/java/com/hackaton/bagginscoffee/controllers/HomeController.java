package com.hackaton.bagginscoffee.controllers;

import com.hackaton.bagginscoffee.codes.PostJSON;
import com.hackaton.bagginscoffee.parsers.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String MainController(Model model) throws IOException {

        Parser parser = new Parser();
        Parser.fillData();

        PostJSON pred = new PostJSON();

        model.addAttribute("prediction", pred);
        model.addAttribute("parser", parser);
        return "home";
    }
}
