package com.hackaton.bagginscoffee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
    @GetMapping("/faq")
    public String FaqController(Model model) {return "faq";}
}
