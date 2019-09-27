package com.github.dariozubaray.springboot.controllers;

import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.dariozubaray.springboot.models.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DesignTacoController.class);

    @GetMapping("/current")
    public String orderForm(Model model) {
        log.debug("orderForm");
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}