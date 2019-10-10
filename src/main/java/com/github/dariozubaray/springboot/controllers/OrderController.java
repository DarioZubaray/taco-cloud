package com.github.dariozubaray.springboot.controllers;

import java.security.Principal;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.github.dariozubaray.springboot.models.Order;
import com.github.dariozubaray.springboot.models.User;
import com.github.dariozubaray.springboot.repository.OrderRepository;
import com.github.dariozubaray.springboot.repository.UserRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DesignTacoController.class);

    private OrderRepository orderRepo;
    private UserRepository userRepo;

    public OrderController(OrderRepository orderRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
            SessionStatus sessionStatus, Principal principal) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        User user = userRepo.findByUsername(principal.getName());
        order.setUser(user);

        log.info("Order submitted: " + order);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
